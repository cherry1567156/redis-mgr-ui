package com.xianxin.redis.admin.controller;

import com.xianxin.redis.admin.bean.dto.UserDto;
import com.xianxin.redis.admin.bean.vo.LoginUserVo;
import com.xianxin.redis.admin.framework.common.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 贤心i
 * @email 1138645967@qq.com
 * @date 2020/01/29
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @RequestMapping("/login")
    public Response<UserDto> login(@RequestBody LoginUserVo vo) {
        UserDto userDto = new UserDto();
        userDto.setNickname("admin");
        return Response.success(200, 1, "SUCCESS").setData(userDto);
    }

}
