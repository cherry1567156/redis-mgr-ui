package com.xianxin.redis.admin;

import com.xianxin.redis.admin.bean.po.SysRedis;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

@SpringBootTest
class RedisAdminApplicationTests {

    @Test
    void contextLoads() {
        //redis://:foobared@10.0.50.11:6379/1
//        JedisShardInfo shardInfo = new JedisShardInfo("redis://:bati@192.168.1.60:6379/0");
        StopWatch watch = new StopWatch();
        watch.start();
        JedisShardInfo shardInfo = new JedisShardInfo("redis://:bati@192.168.16.94:6379/0");
//        shardInfo.setPassword("bati");
        Jedis jedis = new Jedis(shardInfo);
        Long dbsize = jedis.dbSize();
        System.out.println("dbsize=" + dbsize);
        String json = jedis.hget("D_INS_CONFIG:APP_CITY", "340100");
        System.out.println(json);

//        Set<String> keys = jedis.keys("D_INS*");
//        System.out.println("keys.size=" + keys.size());
//        keys.forEach(key -> {
//            String type = jedis.type(key);
//            System.out.println("key=" + key + ", type=" + type);
//        });

//        String scanRet = "0";
        ScanParams scanParams = new ScanParams();
//        int count = 10000;
//        scanParams.count(count);
//        String match = "D_INS_CONFIG*";
////        String match = "*";
//        scanParams.match(match);
//        List<String> keys = new ArrayList<>();
//
//        do {
//            ScanResult<String> scanResult = jedis.scan(scanRet, scanParams);
//            scanRet = scanResult.getCursor();
//            //scan 487439 MATCH * COUNT 10000
//            System.out.println("scan " + scanRet + " MATCH " + match + " COUNT " + count);
//            keys.addAll(scanResult.getResult());
//        } while (!scanRet.equals("0"));
//
//        System.out.println(keys.size());
//
//        List<Map<String, Object>> keyMap = new ArrayList<>();
//        keys.forEach(key -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put("key", key);
//            String type = jedis.type(key);
//            map.put("type", type);
//            keyMap.add(map);
//        });
//
//        System.out.println(keyMap.size());


        Long total = jedis.llen("*");
        System.out.println("total=" + total);

        watch.stop();
        double time = watch.getTotalTimeSeconds();
        System.out.println("耗时：" + time + "s");
    }


    @Test
    public void test() {
        JedisShardInfo shardInfo = new JedisShardInfo("redis://:bati@192.168.1.60:6379/0");
        StopWatch watch = new StopWatch();
        watch.start();
        Jedis jedis = new Jedis(shardInfo);
        List<String> ls = jedis.lrange("*", 10, 10);


        System.out.println(ls.size());
    }

    @Test
    public void selectPage() {

        selectPage("test_set", 1, 10);

    }

    private void selectPage(String keyword, int pageNo, int pageSize) {
        JedisShardInfo shardInfo = new JedisShardInfo("redis://:bati@192.168.1.60:6379/0");
        Jedis jedis = new Jedis(shardInfo);
        ScanParams scanParams = new ScanParams();
        int count = 10000;
        scanParams.count(count);
        String match = "*";
        if (StringUtils.isNotBlank(keyword)) {
            match = keyword + "*";
        }
        scanParams.match(match);
        String scanRet = "0";

        List<String> keyList = new ArrayList<>();
        do {
            ScanResult<String> scanResult = jedis.scan(scanRet, scanParams);
            scanRet = scanResult.getCursor();
            //scan 487439 MATCH * COUNT 10000
            System.out.println("scan " + scanRet + " MATCH " + match + " COUNT " + count);

            keyList.addAll(scanResult.getResult());
        } while (!scanRet.equals("0"));

        if (!CollectionUtils.isEmpty(keyList)) {
            List<SysRedis> list = new ArrayList<>();
            int start = pageNo == 1 ? 0 : (pageNo - 1) * pageSize;
            for (int i = start, j = 0; i < keyList.size() && j < pageSize; i++, j++) {
                String key = keyList.get(i);

                SysRedis base = baseInfo(key, jedis);
                list.add(base);
            }


            System.out.println(list.size());
        }
    }

    private SysRedis baseInfo(String key, Jedis jedis) {
        String type = jedis.type(key);
        Long expire = jedis.ttl(key);
        System.out.println("key=" + key + "，type=" + type);
        SysRedis sysRedis = new SysRedis(type, key, null, String.valueOf(expire));
        Long elCount = 0L;
        if ("string".equals(type)) {
            elCount = 1L;
        } else if ("list".equals(type)) {
            elCount = jedis.llen(key);
        } else if ("hash".equals(type)) {
            elCount = jedis.hlen(key);
        } else if ("set".equals(type)) {
            elCount = jedis.scard(key);
        } else if ("zset".equals(type)) {
            elCount = jedis.zcard(key);
        }
        sysRedis.setElCount(elCount);
        return sysRedis;
    }
}
