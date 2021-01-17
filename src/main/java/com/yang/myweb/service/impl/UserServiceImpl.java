package com.yang.myweb.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yang.myweb.common.RedisHelper;
import com.yang.myweb.config.WebConfig;
import com.yang.myweb.entity.User;
import com.yang.myweb.enums.ResultCode;
import com.yang.myweb.mapper.UserMapper;
import com.yang.myweb.service.UserService;
import com.yang.myweb.util.EncryptionUtil;
import com.yang.myweb.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * 用户服务
 *
 * @author Amars
 * @date 2021-01-10
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;
    private final RedisHelper redisHelper;
    private final WebConfig webConfig;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RedisHelper redisHelper, WebConfig webConfig) {
        this.userMapper = userMapper;
        this.redisHelper = redisHelper;
        this.webConfig = webConfig;
    }

    @Override
    public Result<?> signIn(String userName, String userPassword) {
        // 从redis缓存获取用户信息
        User user = (User) redisHelper.getMapValue(userName);
        String sss = user.getUserId();
        if (user == null) {
            user = Optional.ofNullable(userMapper.getUserByName(userName)).orElse(new User());
            redisHelper.setValue(user.getUserName(), user);
        }
        try {
            String cipherPassword = EncryptionUtil.getCiphertext(userPassword, webConfig.getSalt());
            if (cipherPassword.equals(user.getUserPassword())) {
                return Result.success(ResultCode.SUCCESS);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            LOGGER.error("Encrypt error: {}", ex);
        }
        return Result.failure(ResultCode.USER_LOGIN_ERROR);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> signUp(String userInfo) {
        try {
            Gson gson = new GsonBuilder().create();
            User user = gson.fromJson(userInfo, User.class);
            user.setUserPassword(EncryptionUtil.getCiphertext(user.getUserPassword(), webConfig.getSalt()));
            user.setUserId(UUID.randomUUID().toString());
            user.setCreateDate(new Date(System.currentTimeMillis()));
            userMapper.insertUser(user);
            LOGGER.info(user.toString());
            return Result.success(ResultCode.SUCCESS);
        } catch (Exception ex) {
            LOGGER.error("Insert user info failed: {}", ex.getMessage());
        }
        return Result.failure(ResultCode.USER_SIGN_UP_FAILED);
    }
}
