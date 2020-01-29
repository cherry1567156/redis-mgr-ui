package com.xianxin.redis.admin.controller;

import com.xianxin.redis.admin.bean.po.SysUser;
import com.xianxin.redis.admin.bean.vo.LoginUserVo;
import com.xianxin.redis.admin.framework.common.Response;
import com.xianxin.redis.admin.framework.config.SysConfig;
import com.xianxin.redis.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 贤心i
 * @email 1138645967@qq.com
 * @date 2020/01/29
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/login")
    public Response<SysUser> login(@RequestBody LoginUserVo vo) {
        SysUser user = SysConfig.findUserByUsername(vo.getUsername());
        if (user != null) {
            if (!vo.getPassword().equals(user.getPassword())) {
                return Response.error("密码错误");
            } else {

                return Response.success(200, 1, user);
            }
        } else {
            return Response.error("用户不存在");
        }
    }

    @GetMapping(path = "/query")
    public Response<List<SysUser>> query() {

        return sysUserService.list();
    }


}
