package com.itdreamworks.systemmanage.config;

public class MapEntity {
    private String path,remote,method;
    private boolean parameters;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        this.remote = path;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean hasParameters() {
        return parameters;
    }

    public boolean getParameters() {
        return parameters;
    }

    public void setParameters(boolean parameters) {
        this.parameters = parameters;
    }

}
