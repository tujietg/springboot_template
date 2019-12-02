package com.tujietg.template.service;

/**
 * @author tujietg
 * @date 2019/12/2 8:32 PM
 */
public interface RedisService {

    /**
     * 设置数据
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    String get(String key);


    /**
     * 设置key的过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     *
     * @param key
     */
    void remove(String key);

    /**
     * 自增操作
     *
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

}
