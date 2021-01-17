package com.yang.myweb.common;

/**
 * 功能描述 CustomNotFoundException
 *
 * @author Daze
 * @date 2021-01-17
 */
public class CustomNotFoundException extends RuntimeException{

    public CustomNotFoundException(String msg) {
        super(msg);
    }
}
