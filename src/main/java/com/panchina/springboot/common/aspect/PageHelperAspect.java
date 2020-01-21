package com.panchina.springboot.common.aspect;

import com.github.pagehelper.PageHelper;

import com.panchina.springboot.common.annotation.Pager;
import com.panchina.springboot.domain.query.BaseQuery;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 分页插件切面
 * Created by lifei on 2020/1/2
 */
@Aspect
@Component
public class PageHelperAspect {
    @Before("@annotation(pager)")
    public void doBefore(JoinPoint point, Pager pager){
        Object[] args = point.getArgs();
        for(Object arg:args){
            if(BaseQuery.class.isAssignableFrom(arg.getClass())){
                BaseQuery pageArg = (BaseQuery) arg;
                PageHelper.startPage(pageArg.getPageNumber(), pageArg.getPageSize(), true);
            }
        }
    }
}
