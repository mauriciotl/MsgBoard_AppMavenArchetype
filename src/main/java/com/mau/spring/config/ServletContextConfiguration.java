package com.mau.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
//@ComponentScan(
//        basePackages = "com.mau",
//        useDefaultFilters = false,
//        includeFilters = @ComponentScan.Filter(Controller.class)
//)
@ComponentScan(basePackages = "com.mau") // This is all you need now
public class ServletContextConfiguration
{
}
