package com.tyq.eshop.inventory.dao;

/**
 * @author tangyingqi
 * @date 2019-09-10
 */
public interface RedisDAO {

    void set(String key,String value);
    String get(String key);
    void delete(String key);
}
