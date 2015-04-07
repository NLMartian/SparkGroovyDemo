package me.nlmartian.java.sparkdemo

import redis.clients.jedis.Jedis

/**
 * Created by nlmartian on 4/7/15.
 */
class RedisClient {
    static redisHost = "localhost"

    static String getString(String key) {
        def jedis = new Jedis(redisHost)
        return jedis.get(key)
    }

    static String putString(String key, String value) {
        def jedis = new Jedis(redisHost)
        return jedis.set(key, value)
    }
}
