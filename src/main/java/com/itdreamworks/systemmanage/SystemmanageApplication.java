package com.itdreamworks.systemmanage;

import com.itdreamworks.systemmanage.filter.ApiFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
@EnableCaching
@ServletComponentScan
public class SystemmanageApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SystemmanageApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SystemmanageApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean contextFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(apiFilter());
		registrationBean.addUrlPatterns("*.api");
		registrationBean.setName("apiFilter");
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean
	public Filter apiFilter() {
		return new ApiFilter();
	}

}
