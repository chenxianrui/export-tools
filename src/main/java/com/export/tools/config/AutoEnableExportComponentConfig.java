package com.export.tools.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

import javax.annotation.Resource;

@Configuration
//用于扫描本包下的所有bean
//@ComponentScan
public class AutoEnableExportComponentConfig {

    //不需要@ComponentScan 也可注册
    @Bean
    @ConditionalOnMissingBean
    public String greeter() {
        return new String();
    }
}
