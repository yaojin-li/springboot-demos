package com.example.demo;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    /**
     * 数据库连接密码 加解密
     * */
    @Test
    void contextLoads() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        // 加密的算法，这个算法是默认的，不需修改！
        config.setAlgorithm("PBEWithMD5AndDES");

        // 修改项：加密的密钥，配置到yml文件中
        config.setPassword("demo");
        standardPBEStringEncryptor.setConfig(config);
        // 修改项：数据库连接密码
        String plainText = "123456789";
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

}
