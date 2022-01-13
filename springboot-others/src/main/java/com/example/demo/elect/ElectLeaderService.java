package com.example.demo.elect;

/**
 * @Description: 选举机制
 * 多个服务节点时，通过选举机制抢占Redis分布式锁选举leader
 * --------------------------------------
 * @ClassName: ElectLeaderService.java
 * @Date: 2021/12/6 0006  9:42
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public interface ElectLeaderService {

    // 开启选举
    boolean startElect();

    // 是否为leader
    boolean isLeader();

    // 停止选举
    boolean stopElect();

    // 获取选举名称
    String getElectNodeName();

}
