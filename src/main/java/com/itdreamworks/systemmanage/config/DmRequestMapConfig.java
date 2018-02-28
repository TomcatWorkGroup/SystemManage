package com.itdreamworks.systemmanage.config;

import java.util.HashMap;


public class DmRequestMapConfig {

    private static HashMap<String,FeignSetting> map = new HashMap(100);

    public static HashMap<String,FeignSetting> getDmRequestMap(){
        return map;
    }

    public static void setDmRequestMap(RequestMap map){
        DmRequestMapConfig.map.clear();
        for(MapEntity item : map.getMap()){
            FeignSetting setting = FeignSetting.getInstance(map.getHost(),map.getExt(),item);
            DmRequestMapConfig.map.put(
                    setting.getLocalPath(),
                    setting
            );
        }
    }
}
