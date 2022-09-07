# springboot-others

### wrapper 部署
- 配置assembly.xml、wrapper配置文件与启动脚本等。

### springboot-jar-war
- 测试jar包部署，及Linux上查看或修改jar包配置文件：_  
  1.vim xxx.jar 搜索修改，同Linux修改zip;  
  2.提取修改再覆盖；jar xf / jar uf  
  3.解压jar包，修改后重新打包
- 测试war包部署，包含war包配置、mybatisPlus、MybatisPlus 分页插件、log aop、druid、lombok、Mybatis-generator插件等_
  1.http://IP:端口/服务/请求映射
  2.端口：Tomcat默认端口，server.xml配置；服务名：webapps中的服务名称


3. retry 异常重试
4. paramStorage 进程间变量存储与获取
5. multiScheduler 多线程执行定时任务池配置
6. integration 基于redis使用Spring Integration实现分布式锁
7. elect 多节点选举机制（redis分布式锁、zookeeper选举）
8. delayedTask 延迟任务（redis、时间轮算法）
