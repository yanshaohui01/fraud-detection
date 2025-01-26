package com.hsbc.springboot.utils;

import lombok.Data;

/**
 * 〈功能概述〉<br>
 *
 * @className: BaseResult
 * @package: com.hsbc.springboot.utils
 * @author: bruce
 * @date: 2025/1/24 18:01
 */
@Data
public class BaseResult<T> {
    private int code;
    private String desc;
    private T data;

    public BaseResult(int code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public static <T, R extends HttpCode> BaseResult<T> success(T data) {
        return new BaseResult(2000, null, data);
    }

    public static <T, R extends HttpCode> BaseResult<T> success(T data, String desc) {
        return new BaseResult(2000, desc, data);
    }

    public static <T, R extends HttpCode> BaseResult<T> fail(String desc) {
        return new BaseResult(3000, desc, null);
    }

    public static <T, R extends HttpCode> BaseResult<T> paramError(String desc) {
        return new BaseResult(4000, desc, null);
    }

    public static <T, R extends HttpCode> BaseResult<T> error(String desc) {
        return new BaseResult(5000, desc, null);
    }

    public static <T, R extends HttpCode> BaseResult<T> authDeny(String desc) {
        return new BaseResult(4100, desc, null);
    }
}
