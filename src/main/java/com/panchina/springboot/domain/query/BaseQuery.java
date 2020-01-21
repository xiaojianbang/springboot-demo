package com.panchina.springboot.domain.query;

import lombok.Data;

/**
 *  基础分页查询参数
 * Created by lifei on 2020/1/2
 */
@Data
public class BaseQuery {

    Integer pageNumber = 1;

    Integer pageSize = 10;

}
