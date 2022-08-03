# springboot-apollo

##
1.Idea 中启动，配置启动参数：
-Dapollo.configService=http://192.168.100.131:8080 -Denv=DEV
注：
    Apollo服务端默认端口为8070，即使用http://[apollo部署服务器IP]:8070即可访问Apollo服务；
    配置启动参数8080为eurka端口，即从eurka中发现注册的Apollo服务；

2.java命令启动：
java -Dapollo.configService=http://172.190.119.83:8080 -Denv=DEV -jar apollo-demo.jar

3. 已集成 AutoUpdateConfigChangeListener 监听器，当Apollo中数据改动且代码中有使用的配置时，监听器有日志输出：
    新增、修改：
    - `2022-08-02 23:35:53.831  INFO 15740 --- [Apollo-Config-1] c.f.a.s.p.AutoUpdateConfigChangeListener : Auto update apollo changed value successfully, new value: 33, key: server.id, beanName: apolloController, field: com.example.demo.controller.ApolloController.serverId`
    删除：采用代码中的默认值：
    - `2022-08-02 23:38:07.048  INFO 15740 --- [Apollo-Config-2] c.f.a.s.p.AutoUpdateConfigChangeListener : Auto update apollo changed value successfully, new value: 默认值, key: test, beanName: apolloController, field: com.example.demo.controller.ApolloController.test`
    

##
构建与配置Apollo配置中心：
https://github.com/ctripcorp/apollo/wiki/Quick-Start
注：端口被占用，检查 lsof -i:8080


参考：
https://blog.csdn.net/wuzhiwei549/article/details/105139608/
https://www.cnblogs.com/cjsblog/archive/2019/05/31/10956364.html

采坑记录：
https://blog.csdn.net/qq_18657175/article/details/104645975
