package com.yang.myweb.common;

/**
 * 功能描述 ResponseMsg
 *
 * @author Daze
 * @date 2021-01-17
 */
public class ResponseMsg {
    private String message;

    public ResponseMsg(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
