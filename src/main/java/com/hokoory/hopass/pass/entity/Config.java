package com.hokoory.hopass.pass.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hokoory
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Config implements Serializable {

    private static final long serialVersionUID=1L;

    private  int id;
    /**
     * key
     */
    private String configKey;

    /**
     * value
     */
    private String configValue;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 创建时间
     */
    private Integer createTime;


}
