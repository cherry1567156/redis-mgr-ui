package com.xianxin.redis.admin.controller;

import com.xianxin.redis.admin.bean.dto.SysRedisDto;
import com.xianxin.redis.admin.bean.po.RedisConfig;
import com.xianxin.redis.admin.bean.po.SysRedis;
import com.xianxin.redis.admin.bean.vo.CacheRedisVo;
import com.xianxin.redis.admin.bean.vo.CacheRedisQueryVo;
import com.xianxin.redis.admin.bean.vo.SysRedisCreateVo;
import com.xianxin.redis.admin.bean.vo.SysRedisUpdateVo;
import com.xianxin.redis.admin.framework.common.Response;
import com.xianxin.redis.admin.service.SysRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 贤心i
 * @email 1138645967@qq.com
 * @date 2020/01/29
 */
@RestController
@RequestMapping(path = "/redis")
public class RedisController extends BaseController{

    @Autowired
    private SysRedisService sysRedisService;

    @GetMapping(path = "/config/query")
    public Response<List<RedisConfig>> queryConfig() {

        return sysRedisService.list();
    }

    @PostMapping(path = "/cache/query")
    public Response<List<SysRedis>> queryCache(@RequestBody CacheRedisQueryVo vo) {

        return sysRedisService.cacheList(vo);
    }

    @PostMapping(path = "/cache/details")
    public Response<SysRedisDto> queryCacheDetails(@RequestBody CacheRedisQueryVo vo) {

        return sysRedisService.cacheDetails(vo);
    }

    @PostMapping(path = "/cache/create")
    public Response cacheCreate(@RequestBody CacheRedisVo vo) {

        return sysRedisService.cacheCreate(vo);
    }

    @PostMapping(path = "/cache/name/update")
    public Response cacheNameUpdate(@RequestBody CacheRedisVo vo) {

        return sysRedisService.cacheNameUpdate(vo);
    }

    @PostMapping(path = "/cache/expire/update")
    public Response cacheExpireUpdate(@RequestBody CacheRedisVo vo) {

        return sysRedisService.cacheExpireUpdate(vo);
    }

    @PostMapping(path = "/cache/value/create")
    public Response cacheValueCreate(@RequestBody CacheRedisVo vo) {

        return sysRedisService.cacheValueCreate(vo);
    }
    @PostMapping(path = "/cache/value/update")
    public Response cacheValueUpdate(@RequestBody CacheRedisVo vo) {

        return sysRedisService.cacheValueUpdate(vo);
    }

    @PostMapping(path = "/cache/delete")
    public Response cacheDelete(@RequestBody CacheRedisVo vo) {

        return sysRedisService.cacheDelete(vo);
    }

    @GetMapping(path = "/config/select")
    public Response<List<Map<String, String>>> select() {

        return sysRedisService.select();
    }

    @PostMapping(path = "/config/create")
    public Response create(@RequestBody SysRedisCreateVo vo) {

        return sysRedisService.save(vo);
    }

    @PostMapping(path = "/config/update")
    public Response update(@RequestBody SysRedisUpdateVo vo) {

        return sysRedisService.update(vo);
    }

    @GetMapping(path = "/config/enabled")
    public Response enabled(String host) {

        return sysRedisService.enabled(host);
    }

    @GetMapping(path = "/config/disabled")
    public Response disabled(String host) {

        return sysRedisService.disabled(host);
    }
    @GetMapping(path = "/config/delete")
    public Response delete(String host) {

        return sysRedisService.delete(host);
    }
}
