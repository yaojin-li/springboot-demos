# springboot-demos
SpringBoot 学习案例汇总。

#### 1. springboot-apollo 
- Apollo基本配置
    -  namespace配置、热部署、配置变动监听、服务器配置文件与Apollo配置文件读取优先级问题的原因；

#### 2. springboot-batch
- 跑批任务；
    - 比较成熟的跑批工具；

#### 3. springboot-db
    - 每种数据库的config配置；结合Apollo单独配置；（MongoDB，MySql，Oracle，SqlServer）
    - 通过子模块分割开，每个模式单独配置；
    - 读写分离；
    - springboot-datasources
        - Druid数据源配置、mysql主从、本地mysql数据源、log4j2、自定义注解、AOP、mybatis generator等_
 
#### 4. springboot-docker
- docker file 分层构建；
    - docker部署示例；

#### 5. springboot-file
- excel
    - easy-excel等；

#### 5. springboot-job
- elastic-job
    - 多活节点任务调度；
    - xxl-job；
    - 其他任务调度工具；

#### springboot-kafka
- kafka config；
    - Apollo继承kafka config 配置；

#### 2. springboot-log
- log4j2、lombok、日志切面、方法日志注解；（IDEA本地开发调试配置日志插件 Grep Console）
    - logback 日志配置（性能对比）、不同日志体系对比、日志锚点（分布式系统日志排除问题）、debug模式；

#### springboot-maven

#### 7. springboot-mybatisPlus
- mybatisPlus、分页插件、log aop、druid、lombok、Mybatis-generator插件；

#### springboot-other 中的内容集中到其他相关子项目中。
- email、

#### springboot-prometheus

#### springboot-rabbitmq

#### 3. springboot-redis
- 单机模式、多个单机模式、集群模式、哨兵模式、lettuce连接池；
    - 通过子模块分割开，每个模式单独配置；scan方法代替keys方式做全量数据匹配；
    - 1.yml等配置文件（方便）；2.Apollo+config配置（灵活）；

#### 4. springboot-scheduler
- 集成scheduler定时任务；
    - 设置定时任务池配置；比较成熟的定时任务工具；
    
#### 5. springboot-request-limit 
- 外部请求限流方式：guava、redis、sentinel；
    - 内部触发次数限流：acquire();
    - 通过子模块分割开；
    - 比较成熟的限流工具；




#### 8. springboot-jar-war
- 测试jar包部署，及Linux上查看或修改jar包配置文件：_  
    1.vim xxx.jar 搜索修改，同Linux修改zip;  
    2.提取修改再覆盖；jar xf / jar uf  
    3.解压jar包，修改后重新打包
- 测试war包部署，包含war包配置、mybatisPlus、MybatisPlus 分页插件、log aop、druid、lombok、Mybatis-generator插件等_
    1.http://IP:端口/服务/请求映射
    2.端口：Tomcat默认端口，server.xml配置；服务名：webapps中的服务名称
  

#### 9. springboot-swagger
- 包含：集成swagger、mybatisPlus、lombok、Mybatis-generator插件等_ 
    swagger-ui控台：http://localhost:8080/swagger-ui.html
    - swagger-bootstrap-ui











