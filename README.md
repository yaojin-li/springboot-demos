# springboot-demos
SpringBoot 学习案例汇总。

#### 1. springboot-apollo 
- Apollo基本配置
    -  namespace配置、热部署、配置变动监听、服务器配置文件与Apollo配置文件读取优先级问题的原因；

#### 2. springboot-log4j2 
- log4j2、lombok、日志切面、方法日志注解；（IDEA本地开发调试配置日志插件 Grep Console）
    - logback 日志配置（性能对比）、不同日志体系对比、日志锚点（分布式系统日志排除问题）、debug模式；

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

#### 6. 


4. springboot-mongodb

    _包含：集成MongoDB数据库，使用springframework中的MongoDB模板创建连接等_
    
5. springboot-mysql

    _包含：集成mysql数据库、Druid连接池、mybatis generator等_
    
6. springboot-oracle

    _包含：集成oracle数据库、Druid连接池、mybatis generator等_
    
7. springboot-sqlserver

    _包含：集成sqlserver数据库、Druid连接池、mybatis generator等_
    
8. springboot-jar

    _包含：主要测试jar包部署，及Linux上查看或修改jar包配置文件：_  
    1.vim xxx.jar 搜索修改，同Linux修改zip;  
    2.提取修改再覆盖；jar xf / jar uf  
    3.解压jar包，修改后重新打包
    
9. springboot-datasources

    _包含：集成Druid数据源配置、mysql主从、本地mysql数据源、log4j2、自定义注解、AOP、mybatis generator等_
    
10. springboot-mybatisPlus

    _包含：集成mybatisPlus、MybatisPlus 分页插件、log aop、druid、lombok、Mybatis-generator插件等_
    
11. springboot-wardemo

    _包含：主要测试war包部署，包含war包配置、mybatisPlus、MybatisPlus 分页插件、log aop、druid、lombok、Mybatis-generator插件等_
    1.http://IP:端口/服务/请求映射
    2.端口：Tomcat默认端口，server.xml配置；服务名：webapps中的服务名称

12. springboot-swagger

    _包含：集成swagger、mybatisPlus、lombok、Mybatis-generator插件等_ 
    swagger-ui控台：http://localhost:8080/swagger-ui.html

13. springboot-xxl-job
14. 
15. springboot-other 中的内容集中到其他相关子项目中。