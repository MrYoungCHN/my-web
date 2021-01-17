package com.yang.myweb.mapper;

import com.yang.myweb.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Amars
 * @date 2021-01-10
 */
@Repository
public interface UserMapper {
    /**
     * 获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User getUserByName(String userName);

    /**
     * 获取用户信息
     *
     * @param user 用户信息
     * @return 插入结果
     */
    int insertUser(@Param("user") User user);
}
