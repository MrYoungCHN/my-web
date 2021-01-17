package com.yang.myweb.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 功能描述 WebRestControllerAdvice
 *
 * @author Daze
 * @date 2021-01-17
 */
@RestControllerAdvice
public class WebRestControllerAdvice {
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseMsg handleNotFoundException(CustomNotFoundException ex) {
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
        return responseMsg;
    }
}
