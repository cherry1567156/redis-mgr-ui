package com.xianxin.redis.admin.bean.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 敖燕飞
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RedisConfig implements Serializable {

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 密码
     */
    private String password;

    /**
     * 别名
     */
    private String name;

    /**
     * 可用状态
     */
    private Boolean status;

    /**
     * 环境
     */
    private String env;
}
