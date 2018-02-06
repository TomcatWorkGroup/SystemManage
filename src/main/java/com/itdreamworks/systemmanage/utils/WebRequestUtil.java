package com.itdreamworks.systemmanage.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class WebRequestUtil {
    public static Map<String,String> getRequestParameterMap(HttpServletRequest request){
        Map<String,String[]> m = request.getParameterMap();
        Map<String,String> map = new HashMap<>(m.size());
        for (String key : m.keySet()){
            map.put(key,String.join(",",m.get(key)));
        }
        return map;
    }
}
