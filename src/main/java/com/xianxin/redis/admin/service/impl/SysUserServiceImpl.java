package com.xianxin.redis.admin.service.impl;

import com.xianxin.redis.admin.bean.po.SysUser;
import com.xianxin.redis.admin.framework.common.Response;
import com.xianxin.redis.admin.framework.config.SysConfig;
import com.xianxin.redis.admin.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 敖燕飞
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {


    @Override
    public Response<List<SysUser>> list() {
        return Response.success(SysConfig.userAll());
    }

}
