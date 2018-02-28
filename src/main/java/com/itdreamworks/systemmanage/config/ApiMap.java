package com.itdreamworks.systemmanage.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "customerdatamanage")
public class ApiMap extends RequestMap{

}
