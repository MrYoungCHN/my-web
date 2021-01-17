package com.yang.myweb.controller;

import com.google.gson.Gson;
import com.yang.myweb.common.CustomNotFoundException;
import com.yang.myweb.enums.ResultCode;
import com.yang.myweb.service.UserService;
import com.yang.myweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Amars
 * @date 2021-01-10
 */
@RestController
@RequestMapping(value = "")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("sign_in")
    @ResponseBody
    public Result signIn(@RequestParam(value = "userName") String userName,
                         @RequestParam(value = "userPassword") String userPassword) {
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(userPassword)) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        return userService.signIn(userName, userPassword);
    }

    @PostMapping("sign_up")
    @ResponseBody
    public Result signUp(@RequestBody String userInfo) {
        if (!StringUtils.hasText(userInfo)) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        return userService.signUp(userInfo);
    }
}
