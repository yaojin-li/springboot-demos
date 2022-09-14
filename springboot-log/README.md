## springboot-log
### log4j2
lombok、日志切面、方法日志注解；（IDEA本地开发调试配置日志插件 Grep Console）

### logback
1. logback 日志配置（性能对比）；   
2. logback debug 模式；   
3. 不同日志体系对比；   
4. 分布式日志链路跟踪（锚点 traceId）
    - 拦截器实现--单线程正常，多线程无法追踪；
    - 过滤器实现--单线程正常，多线程无法追踪；
    - TtlMdcAdapter + 拦截器实现--单线程正常，多线程正常；
    - TtlMdcAdapter + 过滤器实现--单线程正常，多线程正常；
    - TtlThreadPoolTaskExecutor 基于Spring的ThreadPoolTaskExecutor，经过ttl修饰后的增强线程池

参考   
https://juejin.cn/post/6981831233911128072 