package com.example.demo.spring;

import com.example.demo.spring.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author yl
 * @Date 2019/12/18 15:24
 * @description:
 */
@Configuration
public class BeanConfig {

    @Bean
    @Scope("prototype")
    public SayHello sayHello() {
        return new SayHello();
    }

    @Bean("showCmd")
    @Conditional(WindowsCondition.class)
    public ShowCmd winCmd() {
        return new WinShowCmd();
    }

    @Bean("showCmd")
    @Conditional(LinuxCondition.class)
    public ShowCmd linuxCmd() {
        return new LinuxShowCmd();
    }

}
