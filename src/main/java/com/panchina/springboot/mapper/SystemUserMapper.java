package com.panchina.springboot.mapper;

import com.panchina.springboot.domain.entity.SystemUser;
import com.panchina.springboot.domain.entity.SystemUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemUserMapper {
    int countByExample(SystemUserExample example);

    int deleteByExample(SystemUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    List<SystemUser> selectByExample(SystemUserExample example);

    SystemUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemUser record, @Param("example") SystemUserExample example);

    int updateByExample(@Param("record") SystemUser record, @Param("example") SystemUserExample example);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);
}