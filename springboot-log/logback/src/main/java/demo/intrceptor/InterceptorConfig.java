//package demo.intrceptor;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @Description: 拦截器配置
// * --------------------------------------
// * @ClassName: InterceptorConfig.java
// * @Date: 2022/5/3 0003  18:00
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Bean
//    public TraceIdInterceptor initTraceIdInterceptor(){
//        return new TraceIdInterceptor();
//    }
//
//    /**
//     * 日志链路追踪拦截器
//     * */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(initTraceIdInterceptor()).addPathPatterns("/**");
//    }
//
//}
