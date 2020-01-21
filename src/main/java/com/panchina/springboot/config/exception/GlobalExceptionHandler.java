package com.panchina.springboot.config.exception;

import com.alibaba.fastjson.JSONPathException;
import com.fasterxml.jackson.core.JsonParseException;
import com.panchina.springboot.common.result.MessageTo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.websocket.DecodeException;
import java.sql.SQLException;

/**
 * 全局异常处理
 *
 * Created by lifei on 2019/12/31
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public MessageTo<Object> exceptionHandler(Throwable e) {

        return doGlobalExceptionHandler(e);
    }

    private MessageTo<Object> doGlobalExceptionHandler(Throwable e) {
        log.error(e.getMessage(), e);

        if (e instanceof BizException) {
            String code = ((BizException) e).getCode();
            String msg = ((BizException) e).getMsg();
            if (null == code) {
                return MessageTo.ofError(msg);
            } else {
                return MessageTo.ofError(code, msg);
            }
        } else if (e instanceof MissingServletRequestParameterException) {
            String parameterName = ((MissingServletRequestParameterException) e).getParameterName();
            return MessageTo.ofError(parameterName + "不能为空");
        } else if (e instanceof BindException){
            return MessageTo.ofError("必传参数填写不完整");
        } else if (e instanceof MethodArgumentNotValidException) {
            return MessageTo.ofError("表单必录数据填写不完整");
        } else if (e instanceof DecodeException) {
            return MessageTo.ofError(e.getMessage());
        } else if (e instanceof ConstraintViolationException) {
            return MessageTo.ofError(e.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            return MessageTo.ofError(e.getMessage());
        } else if (e instanceof NullPointerException) {
            return MessageTo.ofError(e.getMessage());
        } else if (e instanceof MailException) {
            return MessageTo.ofError("邮件发送失败");
        } else if (e instanceof NumberFormatException) {
            return MessageTo.ofError("参数类型转换异常");
        } else if (e instanceof InvalidSystemClockException){
            return MessageTo.ofError("主键生成异常");
        } else if (e instanceof JsonParseException) {
            return MessageTo.ofError("参数类型转换异常");
        } else if (e instanceof JSONPathException) {
            return MessageTo.ofError("类型转换异常");
        } else if (e instanceof BadSqlGrammarException) {
            return MessageTo.ofError("出错啦,请稍后再试!");
        } else if (e instanceof SQLException) {
            return MessageTo.ofError("出错啦,请稍后再试!");
        } else if (e instanceof RuntimeException) {
            return MessageTo.ofError("出错啦,请稍后再试!");
        } else if (e instanceof Exception) {
            return MessageTo.ofError("出错啦,请稍后再试!");
        } else {
            String errorMsg = e.toString() == null ? e.getMessage() : e.toString();
            return MessageTo.ofError(StringUtils.isBlank(errorMsg) ? "未知错误" : errorMsg);
        }
    }
}


