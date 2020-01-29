package com.xianxin.redis.admin.bean.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 贤心i
 * @email 1138645967@qq.com
 * @date 2020/01/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRedisUpdateVo implements Serializable {

    private String host;

    private int port;

    private String password;

    private String name;

    private Boolean status;

    private String env;
}
