package com.xianxin.redis.admin.bean.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 敖燕飞
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseVo implements Serializable {
    private String host;
    private String db;

}
