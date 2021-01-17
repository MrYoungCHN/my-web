package com.yang.myweb.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author Amars
 * @date 2021-01-10
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    /**
     * 序列化ID
     */
    @Serial
    private static final long serialVersionUID = -5809782578272943999L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyDate;
}
