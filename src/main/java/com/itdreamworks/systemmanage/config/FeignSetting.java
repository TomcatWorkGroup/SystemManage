package com.itdreamworks.systemmanage.config;

public class FeignSetting{
    public static final String METHOD_GET="GET";
    public static final String METHOD_POST="POST";

    public static FeignSetting getInstance(String host,String ext, MapEntity entity){
        FeignSetting setting = new FeignSetting();
        setting.hasParameters = entity.hasParameters();
        setting.localPath = String.format("%s%s",entity.getPath(),ext);
        setting.url= String.format("%s%s",host,entity.getRemote());
        setting.method = entity.getMethod();
        return setting;
    }

    private FeignSetting(){

    }
    private String localPath,url,method;
    private boolean hasParameters;

    public String getLocalPath(){
        return localPath;
    }
    public String getUrl() {
        return url;
    }
    public String getMethod() {
        return method;
    }
    public boolean hasParameters() {
        return hasParameters;
    }
}