package demo;

import org.slf4j.TtlMDCAdapterInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        // 自定义ApplicationContextInitializer，用以支持异步程序日志链路追踪
        springApplication.addInitializers(new TtlMDCAdapterInitializer());
        springApplication.run(args);
    }

}
