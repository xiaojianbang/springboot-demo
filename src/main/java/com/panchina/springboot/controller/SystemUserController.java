package com.panchina.springboot.controller;

import com.panchina.springboot.common.result.MessageTo;
import com.panchina.springboot.domain.entity.SystemUser;
import com.panchina.springboot.domain.param.SystemUserParam;
import com.panchina.springboot.domain.query.SysyemUserQuery;
import com.panchina.springboot.mapper.SystemUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lifei on 2020/1/21
 */
@RestController
@RequestMapping("/user")
public class SystemUserController {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @GetMapping("/getUser")
    public MessageTo<SystemUser> getUser (@RequestParam("id") Long id ){
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
        return MessageTo.ofSuccess(systemUser);
    }


    @PostMapping("/addUser")
    public MessageTo<String> addUser(@Validated @RequestBody SystemUserParam param){
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(param,systemUser);
        systemUserMapper.insertSelective(systemUser);
        return MessageTo.ofSuccess();
    }

    @GetMapping("/page")
    public MessageTo<List<SystemUser>> getUserList(@ModelAttribute SysyemUserQuery query){

       return null;
    }

}
