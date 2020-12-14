package com.xianxin.redis.admin.framework.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 * @Author: Cherry
 * @Date: 2020/12/14
 * @Desc: RedisUtil
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisConfig redisConfig;

    public void testConnection(RedisConfig po) {
        try {
            JedisShardInfo shardInfo = new JedisShardInfo("redis://" + redisConfig.getHostName() + ":" + po.getPort() + "/0");
            if (StringUtils.isNotBlank(po.getPassword())) {
                shardInfo.setPassword(po.getPassword());
            } else {
                shardInfo.setPassword(null);
            }
            Jedis jedis = new Jedis(shardInfo);
            jedis.hset("test_connection", po.getHostName(), "success");
            jedis.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public Jedis getJedis(String host, String db) {
        try {
            JedisShardInfo shardInfo = new JedisShardInfo("redis://" + redisConfig.getHostName() + ":" + redisConfig.getPort() + "/" + db);
            if (StringUtils.isNotBlank(redisConfig.getPassword())) {
                shardInfo.setPassword(redisConfig.getPassword());
            } else {
                shardInfo.setPassword(null);
            }
            Jedis jedis = new Jedis(shardInfo);
            return jedis;
        } catch (Exception e) {
            throw e;
        }
    }

}
