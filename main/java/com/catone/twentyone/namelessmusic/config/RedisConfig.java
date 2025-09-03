package com.catone.twentyone.namelessmusic.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

// 这是一个Spring配置类，用于配置与Redis相关的Bean，如RedisTemplate和RedisCacheManager
@Configuration
public class RedisConfig {
    // 定义一个Bean，用于创建RedisTemplate实例，该实例用于操作Redis中的数据
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 初始化一个新的RedisTemplate实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置RedisConnectionFactory，它是与Redis服务器通信的关键组件
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 创建一个ObjectMapper实例，用于JSON数据的序列化和反序列化
        ObjectMapper objectMapper = new ObjectMapper();

        // 创建一个Jackson2JsonRedisSerializer实例，用于序列化和反序列化对象到JSON格式
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

        // 设置属性访问器的可见性，允许所有属性的自动检测
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 激活默认的类型推断，用于处理复杂类型的序列化和反序列化
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);

        // 设置RedisTemplate的默认序列化器为上面创建的Jackson2JsonRedisSerializer
        redisTemplate.setDefaultSerializer(objectJackson2JsonRedisSerializer);
        // 设置键的序列化器为字符串序列化器，确保键是以字符串形式存储
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置值的序列化器为上面创建的Jackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(objectJackson2JsonRedisSerializer);

        // 返回配置好的RedisTemplate实例
        return redisTemplate;

    }

    @Bean // 定义另一个Bean，用于创建RedisCacheManager实例，该实例用于管理Redis中的缓存
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
        RedisSerializer<String> strSerializer = new StringRedisSerializer();

        // 创建一个新的ObjectMapper实例，用于解决缓存转换异常的问题
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        // 创建另一个Jackson2JsonRedisSerializer实例，用于序列化和反序列化缓存的值
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(om, Object.class);

        // 设置属性访问器的可见性，允许所有属性的自动检测
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 激活默认的类型推断，用于处理复杂类型的序列化和反序列化
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        // 定制缓存数据序列化方式及时效
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存项的默认过期时间为一天
                .entryTtl(Duration.ofDays(1))
                // 配置键的序列化方式为字符串序列化器
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(strSerializer))
                // 配置值的序列化方式为上面创建的Jackson2JsonRedisSerializer
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(objectJackson2JsonRedisSerializer))
                // 禁用缓存空值，以节省空间
                .disableCachingNullValues();

        // 使用给定的RedisConnectionFactory构建并返回RedisCacheManager实例
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
    }
}
