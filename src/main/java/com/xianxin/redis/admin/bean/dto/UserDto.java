package com.xianxin.redis.admin.bean.dto;

import lombok.Data;

/**
 * @Author: Cherry
 * @Date: 2020/12/14
 * @Desc: UserDto
 */
@Data
public class UserDto {

    private String username;

    private String password;

    private String avatar;

    private String nickname;

    private String sex;

    private String qq;

    private String tel;

    private String addr;

    private String birth;

    private Boolean status;
}
