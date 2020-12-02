# springboot-demos
SpringBoot 学习案例汇总。

1. springboot-log4j2 

    _包含：log4j2、lombok、日志切面、方法日志注解、常见日志Utils等_  
    _建议 IDEA 配置日志插件 Grep Console 一起使用_
    
2. springboot-rediscluster

    _包含：redis集群模式配置、lettuce连接池等_
    
3. springboot-scheduler

    _包含：集成scheduler定时任务_
    
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
