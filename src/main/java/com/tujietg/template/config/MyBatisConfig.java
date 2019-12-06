package com.tujietg.template.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 配置类
 *
 * @author tujietg
 * @date 2019/11/29 3:03 PM
 */
@Configuration
@MapperScan({"com.tujietg.template.mbg.mapper", "com.tujietg.template.dao"})
public class MyBatisConfig {

}
