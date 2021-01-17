package com.yang.myweb.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yang.myweb.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 功能描述 GlobalExceptionHandle
 *
 * @author Daze
 * @date 2021-01-17
 */
@RestControllerAdvice
class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMsg defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        logger.error("", ex);
        ResponseMsg responseMsg;
        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            responseMsg = new ResponseMsg("404");
        } else {
            responseMsg = new ResponseMsg("500");
        }
        return responseMsg;
    }
}
