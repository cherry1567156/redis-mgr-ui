package com.xianxin.redis.admin.bean.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 贤心i
 * @email 1138645967@qq.com
 * @date 2020/01/29
 */
@Data
public class SysUser implements Serializable {

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
