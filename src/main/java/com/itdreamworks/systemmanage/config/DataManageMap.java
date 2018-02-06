package com.itdreamworks.systemmanage.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "datamanage")
public class DataManageMap extends RequestMap{

}
