package com.xianxin.redis.admin.bean.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 敖燕飞
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CacheRedisQueryVo implements Serializable {

    private String keyword;

    private String cacheKeyword;

    private String type;

    private String db;

    private String host;

    private int pageNo = 1;

    private int pageSize = 10;
}
