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
@MapperScan(value = "com.tujietg.template.mbg.mapper")
public class MyBatisConfig {

}
