package net.soumoney.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by jiangxiaojie on 2017/4/1.
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisPoolConfig getJedisPoolConfig(){
        return new JedisPoolConfig();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getJedisConnectionFactory(){
        return new JedisConnectionFactory(getJedisPoolConfig());
    }
    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(){
        return new StringRedisTemplate(getJedisConnectionFactory());
    }
}
