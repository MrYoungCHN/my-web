package com.yang.myweb.service;


import com.yang.myweb.util.Result;

/**
 * @author Amars
 * @date 2021-01-10
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param userPassword 用户密码
     * @return 登录状态
     */
    Result<?> signIn(String userName, String userPassword);

    /**
     * 用户注册
     *
     * @param userInfo 用户名
     * @return 登录状态
     */
    Result<?> signUp(String userInfo);
}
