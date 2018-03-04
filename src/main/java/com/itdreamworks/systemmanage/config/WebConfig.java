package com.itdreamworks.systemmanage.config;

import com.itdreamworks.systemmanage.filter.ApiFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.Filter;

//@Configuration
//@ImportResource({ "classpath:application.yml"})
public class WebConfig {
//    @Bean
//    public FilterRegistrationBean contextFilterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(apiFilter());
//        registrationBean.addUrlPatterns("*.api");
//        registrationBean.setName("apiFilter");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
//
//    @Bean
//    public Filter apiFilter() {
//        return new ApiFilter();
//    }
}
