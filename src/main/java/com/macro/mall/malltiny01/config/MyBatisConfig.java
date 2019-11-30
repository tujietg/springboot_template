package com.macro.mall.malltiny01.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 配置类
 *
 * @author tujietg
 * @date 2019/11/29 3:03 PM
 */
@Configuration
@MapperScan(value = "com.macro.mall.malltiny01.mbg.mapper")
public class MyBatisConfig {

}
