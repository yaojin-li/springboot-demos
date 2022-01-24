//package com.example.demo.cluster;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisNode;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Description: redis集群模式配置
// * --------------------------------------
// * @ClassName: ClusterConfig.java
// * @Date: 2021/12/22 0022  10:46
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)
//public class ClusterConfig {
//
//    @Value("${spring.redis.cluster.nodes}")
//    private String clusterNodes;
//    @Value("${spring.redis.cluster.max-redirects}")
//    private Integer maxRedirects;
//    @Value("${spring.redis.lettuce.pool.max-active}")
//    private Integer maxActive;
//    @Value("${spring.redis.lettuce.pool.max-idle}")
//    private Integer maxIdle;
//    @Value("${spring.redis.lettuce.pool.min-idle}")
//    private Integer minIdle;
//    @Value("${spring.redis.lettuce.pool.max-wait}")
//    private Long maxWait;
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new StringRedisSerializer());
//        return template;
//    }
//
//    @Bean
//    LettuceConnectionFactory lettuceConnectionFactory() {
//        // 连接池配置
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxIdle(maxIdle == null ? 8 : maxIdle);
//        poolConfig.setMinIdle(minIdle == null ? 1 : minIdle);
//        poolConfig.setMaxTotal(maxActive == null ? 8 : maxActive);
//        poolConfig.setMaxWaitMillis(maxWait == null ? 5000L : maxWait);
//        LettucePoolingClientConfiguration.builder()
//                .poolConfig(poolConfig)
//                .build();
//
//        // redis集群
//        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
//        Set<RedisNode> nodes = new HashSet<>();
//        String[] clusters = clusterNodes.split(",");
//        for (String cluster : clusters) {
//            if (StringUtils.isNotEmpty(cluster)) {
//                String[] info = cluster.split(":");
//                nodes.add(new RedisNode(info[0], Integer.parseInt(info[1])));
//            }
//        }
//        configuration.setClusterNodes(nodes);
//        configuration.setMaxRedirects(maxRedirects);
//        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettuceClientConfiguration.builder();
//        return new LettuceConnectionFactory(configuration, builder.build());
//    }
//
//}
