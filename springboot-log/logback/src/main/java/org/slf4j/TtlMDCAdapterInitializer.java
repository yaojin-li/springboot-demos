package org.slf4j;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description:
 * 自定义ApplicationContextInitializer，用以支持异步程序日志链路追踪
 * --------------------------------------
 * @ClassName: TtlMDCAdapterInitializer.java
 * @Date: 2022/9/14 21:41
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class TtlMDCAdapterInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        // 加载自定义的MDCAdapter
        TtlMdcAdapter.getInstance();
    }
}
