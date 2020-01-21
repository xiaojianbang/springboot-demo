package com.panchina.springboot.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by lifei on 2019/12/31
 */
@Data
public class MessageTo<T> implements Serializable {
    private static final long serialVersionUID = 7338399442514132368L;
    // 数据明细
//    @ApiModelProperty(value = "数据集")
    private T data;
//    @ApiModelProperty(value = "请求状态")
    private Boolean success;
//    @ApiModelProperty(value = "code")
    private String code;
//    @ApiModelProperty(value = "msg")
    private String msg;

    /**
     * 为null时,不参与序列化
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalNum;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageIndex;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPage;


    public static <T> MessageTo<T> ofSuccess() {
        return of(null, true, BaseExceptionEnum.EC00000200);
    }

    public static <T> MessageTo<T> ofSuccess(T data) {
        return of(data, true, BaseExceptionEnum.EC00000200);
    }

    public static <T> MessageTo<T> ofSuccess(T data, String msg) {
        return of(data, true, BaseExceptionEnum.EC00000200.getCode(), msg);
    }

    public static <T> MessageTo<T> ofSuccess(T data, BaseExceptionEnum baseExceptionEnum) {
        return of(data, true, baseExceptionEnum);
    }

    public static <T> MessageTo<T> ofSuccess(T data, Integer totalNum, Integer pageIndex, Integer pageSize) {
        return of(data, true, BaseExceptionEnum.EC00000200, totalNum, pageIndex, pageSize);
    }

    public static <T> MessageTo<T> ofSuccess(T data, Integer totalNum, Integer pageIndex, Integer pageSize, String msg) {
        return of(data, true, BaseExceptionEnum.EC00000200, totalNum, pageIndex, pageSize, msg);
    }

    public static <T> MessageTo<T> of(T data, boolean success, BaseExceptionEnum baseExceptionEnum,
                                      Integer totalNum, Integer pageIndex, Integer pageSize) {
        return of(data, success, baseExceptionEnum, totalNum, pageIndex, pageSize, null);
    }

    public static <T> MessageTo of(T data, boolean success, BaseExceptionEnum baseExceptionEnum) {
        if (null != baseExceptionEnum) {
            return of(data, success, baseExceptionEnum.getCode(), baseExceptionEnum.getMessage());
        } else {
            return of(data, success, null, null);
        }
    }

    public static <T> MessageTo of(T data, boolean success, String code, String msg) {
        MessageTo messageTo = new MessageTo<>();
        messageTo.setData(data);
        messageTo.setSuccess(success);
        messageTo.setCode(code);
        messageTo.setMsg(msg);
        if (data instanceof Collection) {
            messageTo.setTotalNum(((Collection) data).size());
        }
        return messageTo;
    }

    public static <T> MessageTo<T> of(T data, boolean success, BaseExceptionEnum baseExceptionEnum,
                                      Integer totalNum, Integer pageIndex, Integer pageSize, String msg) {

        MessageTo messageTo = new MessageTo();
        messageTo.setData(data);
        messageTo.setSuccess(success);
        if (null != baseExceptionEnum) {
            messageTo.setCode(baseExceptionEnum.getCode());
            messageTo.setMsg(baseExceptionEnum.getMessage());
        }
        if (data instanceof Collection && null == totalNum) {
            messageTo.setTotalNum(((Collection) data).size());
        }
        messageTo.setTotalNum(totalNum);
        messageTo.setPageIndex(pageIndex);
        messageTo.setPageSize(pageSize);
        messageTo.setTotalPage((null == pageSize || pageSize == 0) ? null : (totalNum % pageSize == 0 ? totalNum / pageSize : (totalNum / pageSize + 1)));
        messageTo.setMsg(msg);
        return messageTo;
    }


    public static MessageTo ofError(BaseExceptionEnum baseExceptionEnum) {
        return ofError(baseExceptionEnum.getCode(), baseExceptionEnum.getMessage());
    }
//    public static MessageTo ofLogin(BaseExceptionEnum baseExceptionEnum){
//        return ofLogin(baseExceptionEnum.getCode(),baseExceptionEnum.getMessage());
//    }

    public static MessageTo ofError(String msg) {
        return ofError(BaseExceptionEnum.EC00000500.getCode(), msg);
    }

    public static MessageTo ofError(String code, String msg) {
        MessageTo messageTo = new MessageTo();
        messageTo.setSuccess(false);
        messageTo.setCode(code);
        messageTo.setMsg(msg);
        messageTo.setData(null);
        return messageTo;
    }

    public static <T> MessageTo<List<T>> ofPageInfo(PageInfo<T> pageInfo) {
        return ofSuccess(pageInfo.getList(), (int) pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }

}
