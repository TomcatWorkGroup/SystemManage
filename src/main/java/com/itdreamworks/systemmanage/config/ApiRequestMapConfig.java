package com.itdreamworks.systemmanage.config;

import java.util.HashMap;


public class ApiRequestMapConfig {

    private static HashMap<String,FeignSetting> map = new HashMap(100);

    public static HashMap<String,FeignSetting> getRequestRouteMap(){
        return map;
    }

    public static void setRequestRouteMap(CustomerDataManageMap map){
        ApiRequestMapConfig.map.clear();
        for(MapEntity item : map.getMap()){
            FeignSetting setting = FeignSetting.getInstance(map.getHost(),map.getExt(),item);
            ApiRequestMapConfig.map.put(
                    setting.getLocalPath(),
                    setting
            );
        }
    }
}
