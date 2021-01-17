package com.yang.myweb.util;


import com.yang.myweb.enums.ResultCode;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 输出结果的封装
 * 只要get不要set,进行更好的封装
 *
 * @param <T>
 * @author Amars
 */
@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result() {
    }

    private Result(String msg, int code) {
        if (StringUtils.hasText(msg)) {
            return;
        }
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     * 成功时
     *
     * @return
     */
    public static <T> Result<T> success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功时
     *
     * @param <T> 返回的数据
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     *
     * @param resultCode
     * @return
     */
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}
