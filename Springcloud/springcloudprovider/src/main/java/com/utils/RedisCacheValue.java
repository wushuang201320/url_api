package com.utils;

/**
 * 缓存策略值,用于Spring Cacheable注解的value.
 * @author jk
 * @Date 2019/1/18 14:05
 */
public class RedisCacheValue {
    /**
     * 默认缓存30秒过期.
     */
    public static final String CACHE_KEY = "cache";
    /**
     * 1分钟过期.
     */
    public static final String CACHE_ONE_MINUTES_KEY = "cache_1m";
    /**
     * 5分钟过期.
     */
    public static final String CACHE_FIVE_MINUTES_KEY = "cache_5m";
    /**
     * 10分钟过期.
     */
    public static final String CACHE_TEN_MINUTES_KEY = "cache_10m";
    /**
     * 30分钟过期.
     */
    public static final String CACHE_THIRTY_MINUTES_KEY = "cache_30m";
    /**
     * 1小时过期.
     */
    public static final String CACHE_ONE_HOUR_KEY = "cache_1h";
    /**
     * 5小时过期.
     */
    public static final String CACHE_FIVE_HOUR_KEY = "cache_5h";
    /**
     * 1天过期.
     */
    public static final String CACHE_ONE_DAY_KEY = "cache_1d";
    /**
     * 1周过期.
     */
    public static final String CACHE_ONE_WEEK_KEY = "cache_1w";
    /**
     * 1个月过期.
     */
    public static final String CACHE_ONE_MONTH_KEY = "cache_1mon";
    /**
     * 3个月过期.
     */
    public static final String CACHE_THREE_MONTH_KEY = "cache_3mon";
}
