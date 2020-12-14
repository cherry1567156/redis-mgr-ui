package com.xianxin.redis.admin.service;

import com.xianxin.redis.admin.bean.dto.SysRedisDto;
import com.xianxin.redis.admin.bean.po.SysRedis;
import com.xianxin.redis.admin.bean.vo.CacheRedisQueryVo;
import com.xianxin.redis.admin.bean.vo.CacheRedisVo;
import com.xianxin.redis.admin.bean.vo.SysRedisCreateVo;
import com.xianxin.redis.admin.bean.vo.SysRedisUpdateVo;
import com.xianxin.redis.admin.framework.common.Response;
import com.xianxin.redis.admin.framework.config.RedisConfig;

import java.util.List;
import java.util.Map;

/**
 * @author 敖燕飞
 */
public interface SysRedisService {

    boolean saveOrUpdate(RedisConfig po, boolean conn);

    boolean enabledOrDisabled(String host, String status);

    Response enabled(String host);

    Response disabled(String host);

    Response save(SysRedisCreateVo vo);

    Response update(SysRedisUpdateVo vo);

    Response<List<RedisConfig>> list();

    Response<List<Map<String, String>>> select();

    Response<List<SysRedis>> cacheList(CacheRedisQueryVo vo);

    Response<SysRedisDto> cacheDetails(CacheRedisQueryVo vo);

    Response cacheCreate(CacheRedisVo vo);

    Response cacheDelete(CacheRedisVo vo);

    Response cacheNameUpdate(CacheRedisVo vo);

    Response cacheValueCreate(CacheRedisVo vo);

    Response cacheValueUpdate(CacheRedisVo vo);

    Response cacheExpireUpdate(CacheRedisVo vo);

    Response delete(String host);

    Response selectDb(String host);
}
