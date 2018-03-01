package com.itdreamworks.systemmanage.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "customerdatamanage")
public class CustomerDataManageMap extends RequestMap{
    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    private String enter;

    public List<CustomerDataManageMapEntity> getMap() {
        return entities;
    }

    public void setMap(List<CustomerDataManageMapEntity> entities) {
        this.entities = entities;
    }

    private List<CustomerDataManageMapEntity> entities = new ArrayList<>();
}
