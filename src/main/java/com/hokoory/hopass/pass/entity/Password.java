package com.hokoory.hopass.pass.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hokoory
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Password implements Serializable {

    private static final long serialVersionUID=1L;
    @TableId(type = IdType.INPUT)
    private Integer id;
    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String context;

    /**
     * 密码对应那个网站
     */
    private String web;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码  密码通过自有加密算法
     */
    private String password;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 修改时间
     */
    private Integer updateTime;


}
