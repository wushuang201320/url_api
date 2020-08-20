package com.utils;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author hayder
 * @ClassName: RedisUtil
 * @Description: TODO redis管理工具
 * @date: 2017年10月10日 下午3:08:07
 */
@Configuration
public class RedisUtil {

    /**
     * redis.
     */
    private RedisTemplate redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        this.redisTemplate = redisTemplate;
    }

    /**
     * 批量删除对应的value.
     *
     * @param keys 键
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key.
     *
     * @param pattern 正则表达式
     */
    @SuppressWarnings("unchecked")
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value.
     *
     * @param key 键
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 删除对应的value.
     *
     * @param keys list
     */
    @SuppressWarnings("unchecked")
    public void remove(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 判断缓存中是否有对应的value.
     *
     * @param key 键
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存.
     *
     * @param key 键
     * @return object
     */
    @SuppressWarnings("unchecked")
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存.
     *
     * @param key   键
     * @param value 值
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存.
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 增加.
     *
     * @param key   键
     * @param delta 增量
     * @return 增加后的结果
     */
    @SuppressWarnings("unchecked")
    public Long increment(final String key, final long delta) {
        Long result = null;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.increment(key, delta);

        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 存在key则增加.
     *
     * @param key   键
     * @param delta 增量
     * @return 增加后的结果
     */
    @SuppressWarnings("unchecked")
    public Long incrementExist(final String key, final long delta) {
        if (exists(key)) {
            return increment(key, delta);
        }
        return null;
    }

    /**
     * 设置key过期时间.
     *
     * @param key        键
     * @param expireTime 过期时间
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean expireTime(final String key, Long expireTime) {
        boolean bool = false;
        try {
            bool = Optional.ofNullable(redisTemplate.expire(key, expireTime, TimeUnit.SECONDS)).orElse(false);
        } catch (Exception e) {

        }
        return bool;
    }


    /**
     * 如果密钥不存在，则设置密钥以保持字符串值和到期超时.
     *
     * @param key     键
     * @param value   值
     * @param exptime 有效时间秒
     * @return boolean
     */
    public boolean setNX(final String key, final Serializable value, final long exptime) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            return Optional.ofNullable(operations.setIfAbsent(key, value, Duration.ofSeconds(exptime))).orElse(false);
        } catch (Exception e) {

            return false;
        }
    }


    /**
     * 基础分布式锁.
     *
     * @param key        key值
     * @param lockExpire 秒
     * @return 是否获取到
     */
    public boolean lock(String key, long lockExpire) {
        // 利用lambda表达式
        Boolean execute = (Boolean) redisTemplate.execute((RedisCallback) connection -> {

            long expireAt = System.currentTimeMillis() + lockExpire * 1000;

            return connection.setNX(key.getBytes(), String.valueOf(expireAt).getBytes());
        });
        return execute == null ? false : execute;
    }




    //后续所有是为了解决乱码的代码，是一个整体
    private RedisSerializer<String> keySerializer() {

        return new StringRedisSerializer();

    }

    //使用Jackson序列化器

    private RedisSerializer<Object> valueSerializer() {

        return new GenericJackson2JsonRedisSerializer();

    }

    @Bean

    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        //缓存配置对象

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        redisCacheConfiguration = redisCacheConfiguration
                /*.entryTtl(Duration.ofMinutes(30L)) //设置缓存的默认超时时间：30分钟*/

                .disableCachingNullValues()             //如果是空值，不缓存

                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))         //设置key序列化器

                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((valueSerializer())));  //设置value序列化器

        return RedisCacheManager

                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))

                .cacheDefaults(redisCacheConfiguration).build();

    }

}
