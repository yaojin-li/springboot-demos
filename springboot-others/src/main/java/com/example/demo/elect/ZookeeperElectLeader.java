package com.example.demo.elect;

import com.example.demo.utils.IPUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 通过zookeeper实现选举机制
 * --------------------------------------
 * @ClassName: ZookeeperElectLeader.java
 * @Date: 2022/1/4 0004  19:31
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class ZookeeperElectLeader implements ElectLeaderService {
    private static Logger LOGGER = LoggerFactory.getLogger(ZookeeperElectLeader.class);

    @Value("${zookeeper.master.node.path}")
    private String zkMasterNodePath;

    @Value("${zookeeper.hosts}")
    private String zkHosts;

    /**
     * 选举节点
     */
    private final String electNodeName = IPUtil.getLocalHostIdentifier() + "_" + UUID.randomUUID().toString().replace("_", "");

    /**
     * 是否是主节点
     */
    private volatile boolean leader = false;

    /**
     * 节点是否是运行状态
     */
    private volatile boolean running = false;

    private CuratorFramework curatorFramework = null;

    private Thread electNodeReportThread;
    private LeaderLatch leaderLatch;

    @Override
    public boolean startElect() {
        if (running) {
            return false;
        }
        LOGGER.info("curatorFramework 开始连接zookeeper....");
        curatorFramework = CuratorFrameworkFactory.newClient(zkHosts, new RetryForever(30 * 1000));
        curatorFramework.start();
        try {
            boolean connected = curatorFramework.blockUntilConnected(15, TimeUnit.SECONDS);
            if (!connected) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            LOGGER.error("curatorFramework 启动zookeeper失败....");
            try {
                curatorFramework.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(String.format("zookeeper[%s]连接失败！！", zkHosts));
        }

        // 该节点参加选举工作
        elect();

        running = true;

        // 开起线程输出此时zk集群信息
        electNodeReportThread = new Thread(new NodeReportTask());
        electNodeReportThread.start();
        return false;
    }

    private void elect() {
        leaderLatch = new LeaderLatch(curatorFramework, zkMasterNodePath, electNodeName);
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                LOGGER.info("===========本节点为master节点=============");
                // TODO 分布式系统针对主节点执行调度任务
                // 业务处理...

                leader = true;
            }

            @Override
            public void notLeader() {
                LOGGER.info("本节点非master节点.....");
                // TODO 分布式系统针对主节点关闭调度任务
                // 业务处理...

                leader = false;
            }
        });

        try {
            leaderLatch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private class NodeReportTask implements Runnable {

        @Override
        public void run() {
            while (running) {
                if (leaderLatch != null) {
                    try {
                        Collection<Participant> participants = leaderLatch.getParticipants();
                        StringBuilder sb = new StringBuilder();
                        for (Participant participant : participants) {
                            if (participant.isLeader()) {
                                sb.insert(0, "主节点：" + participant.getId() + ";");
                            } else {
                                sb.append("从节点：" + participant.getId());
                            }
                        }
                        LOGGER.info(String.format("zookeeper集群信息[%s]", sb.toString()));
                        TimeUnit.SECONDS.sleep(3 * 60);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    public boolean isLeader() {
        return leader;
    }

    @Override
    public boolean stopElect() {
        running = false;
        if (leaderLatch != null) {
            CloseableUtils.closeQuietly(leaderLatch);
            leaderLatch = null;
        }
        if (curatorFramework != null) {
            CloseableUtils.closeQuietly(curatorFramework);
            curatorFramework = null;
        }
        if (leader) {
            leader = false;
        }
        LOGGER.info("zookeeper 选举主节点连接已断开....");
        return true;
    }

    @Override
    public String getElectNodeName() {
        return electNodeName;
    }

}
