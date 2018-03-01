package com.itdreamworks.systemmanage.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "datamanage")
public class DataManageMap extends RequestMap{
    public List<DataManageMapEntity> getMap() {
        return entities;
    }

    public void setMap(List<DataManageMapEntity> entities) {
        this.entities = entities;
    }

    private List<DataManageMapEntity> entities = new ArrayList<>();

}
