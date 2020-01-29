package com.xianxin.redis.admin.service;

import com.xianxin.redis.admin.bean.po.SysUser;
import com.xianxin.redis.admin.framework.common.Response;

import java.util.List;

public interface SysUserService {
    Response<List<SysUser>> list();
}
