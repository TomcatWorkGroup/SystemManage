package com.itdreamworks.systemmanage.config;

import java.util.ArrayList;
import java.util.List;

public class RequestMap {
    public static final String METHOD_GET="GET";
    public static final String METHOD_POST="POST";
    private String host,ext;

    public List<MapEntity> getMap() {
        return entities;
    }

    public void setMap(List<MapEntity> entities) {
        this.entities = entities;
    }

    private List<MapEntity> entities = new ArrayList<>();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
