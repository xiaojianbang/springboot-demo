package com.panchina.springboot.common.result;

/**
 * @author lifei
 * @Date 2019/8/2
 */
public enum BaseExceptionEnum {
    NOT_LOGIN("NOT_LOGIN", "操作会话已失效，请重新登录！"),
    NOT_PERMISSION("NOT_PERMISSION", "您无该权限"),
    NOT_COMMENT_CHECK("NOT_COMMENT_CHECK","存在敏感词汇"),

    OPERATION_FAIL("OPERATION_FAIL","操作失败"),

    EC00000200("200", "success"),
    EC00000500("500", "系统异常"),
    EC00000401("401", "权限不足"),
    EC00000400("400", "参数错误");



    private String code;

    private String message;

    BaseExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
