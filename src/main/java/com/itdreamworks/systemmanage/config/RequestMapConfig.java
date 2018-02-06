package com.itdreamworks.systemmanage.config;

import java.util.HashMap;


public class RequestMapConfig {

    private static HashMap<String,FeignSetting> dataManageRequestMap = new HashMap(100);

    public static HashMap<String,FeignSetting> getDataManageRequestMap(){
        return dataManageRequestMap;
    }

    public static void setDataManageRequestMap(RequestMap map){
        dataManageRequestMap.clear();
        for(MapEntity item : map.getMap()){
            FeignSetting setting = FeignSetting.getInstance(map.getHost(),map.getExt(),item);
            dataManageRequestMap.put(
                    setting.getLocalPath(),
                    setting
            );
        }
    }
}
