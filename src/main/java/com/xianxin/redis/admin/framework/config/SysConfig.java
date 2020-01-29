package com.xianxin.redis.admin.framework.config;

import cn.hutool.core.io.file.FileWriter;
import com.alibaba.fastjson.JSON;
import com.xianxin.redis.admin.bean.po.RedisConfig;
import com.xianxin.redis.admin.bean.po.SysUser;
import com.xianxin.redis.admin.framework.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.io.File;
import java.util.*;


/**
 * @author 敖燕飞
 */
@Slf4j
public class SysConfig {

    private static Map<String, SysUser> userMap = new HashMap<>();

    private static Map<String, RedisConfig> redisInfo = new HashMap<>();

    public static SysUser findUserByUsername(String username) {
        return userMap.get(username);
    }

    public static SysUser save(SysUser user) {
        return userMap.put(user.getUsername(), user);
    }

    public static List<SysUser> userAll() {
        List<SysUser> list = new ArrayList<>();
        userMap.values().forEach(user -> {
            list.add(user);
        });
        return list;
    }

    public static void saveOrUpdateRedisConfig(RedisConfig po) {
        redisInfo.put(po.getHost(), po);
//        if (redisInfo != null && redisInfo.size() > 0) {
//            List<RedisConfig> redisConfigList = new ArrayList<>();
//
//            for (RedisConfig redisConfig : redisInfo.values()) {
//                redisConfigList.add(redisConfig);
//            }
//            // 全部重新写入文件
//            writerConfigJsonFile(redisConfigList);
//        }
        writerConfigJsonFile(redisInfo);
    }
    private static void writerConfigJsonFile(Map<String, RedisConfig> redisInfo) {
        if (redisInfo != null && redisInfo.size() > 0) {
            List<RedisConfig> redisConfigList = new ArrayList<>();

            for (RedisConfig redisConfig : redisInfo.values()) {
                redisConfigList.add(redisConfig);
            }
            // 全部重新写入文件
            writerConfigJsonFile(redisConfigList);
        }
    }

    private static void writerConfigJsonFile(List<RedisConfig> redisConfigList) {
        File userFile = new File("./conf/config.json");
        FileWriter writer = new FileWriter(userFile);
        String userJson = JSON.toJSONString(redisConfigList);
        writer.write(userJson);
        log.info("config.json 数据重写成功");
    }

    public static RedisConfig findRedisConfigByUsername(String host) {
        return redisInfo.get(host);
    }

    public static Collection<RedisConfig> getRedisConfigValues() {
        return redisInfo.values();
    }

    public static void loadUserInfoIntoMemory(String userJson) {
        if (StringUtils.isNotBlank(userJson)) {
            List<SysUser> sysUserList = JSON.parseArray(userJson, SysUser.class);
            if (sysUserList != null && sysUserList.size() > 0) {
                sysUserList.forEach(user -> {
                    save(user);
                });
            } else {
                loadDefaultUserInfo();
            }
        } else {
            loadDefaultUserInfo();
        }

    }

    private static void loadDefaultUserInfo() {
        // 系统默认用户信息
        SysUser user = new SysUser();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setNickname("贤心i");
        user.setAvatar("http://q1.qlogo.cn/g?b=qq&nk=1138645967&s=100");
        user.setStatus(true);
        user.setAddr("上海市静安区灵石路656号");
        user.setSex("男");
        user.setTel("15522886118");
        user.setQq("1138645967@qq.com");
        user.setBirth("");

        save(user);
        // 写入user.json 文件
        File userFile = new File("./conf/user.json");
        FileWriter writer = new FileWriter(userFile);
        List<SysUser> userList = Arrays.asList(user);
        String userJson = JSON.toJSONString(userList);
        writer.write(userJson);
    }

    public static void loadRedisConfigIntoMemory(String configJson) {
        if (StringUtils.isNotBlank(configJson)) {
            List<RedisConfig> redisConfigList = JSON.parseArray(configJson, RedisConfig.class);
            if (redisConfigList != null && redisConfigList.size() > 0) {
                redisConfigList.forEach(redisConfig -> {
                    saveOrUpdateRedisConfig(redisConfig);
                });
            } else {
                loadDefaultRedisConfig();
            }
        } else {
            loadDefaultRedisConfig();
        }
    }

    private static void loadDefaultRedisConfig() {
        // 系统默认redis config
        RedisConfig config = new RedisConfig();
        config.setHost("127.0.0.1");
        config.setName("本地redis");
        config.setPort(6379);
        config.setPassword("");
        config.setStatus(true);
        config.setEnv("local");

        saveOrUpdateRedisConfig(config);
        // 写入user.json 文件
        File configFile = new File("./conf/config.json");
        FileWriter writer = new FileWriter(configFile);
        List<RedisConfig> redisConfigList = Arrays.asList(config);
        String userJson = JSON.toJSONString(redisConfigList);
        writer.write(userJson);
    }

    public static void testConnection(RedisConfig po) {
        try {
            JedisShardInfo shardInfo = new JedisShardInfo("redis://" + po.getHost() + ":" + po.getPort() + "/0");
            shardInfo.setPassword(po.getPassword());
            Jedis jedis = new Jedis(shardInfo);
            jedis.hset("test_connection", po.getHost(), "success");
            jedis.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static Jedis getJedis(String host, String db) {
        try {
            RedisConfig redisConfig = SysConfig.findRedisConfigByUsername(host);
            if (redisConfig == null) {
//            return Response.error("配置信息不存在");
            }
            JedisShardInfo shardInfo = new JedisShardInfo("redis://" + redisConfig.getHost() + ":" + redisConfig.getPort() + "/" + db);
            shardInfo.setPassword(redisConfig.getPassword());
            Jedis jedis = new Jedis(shardInfo);
            return jedis;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void deleteRedisConfig(String host) {
       boolean containsKey = redisInfo.containsKey(host);
       if(containsKey){
           redisInfo.remove(host);
           writerConfigJsonFile(redisInfo);
       }else{
           throw new ServerException("配置不存在，删除失败");
       }
    }
}
