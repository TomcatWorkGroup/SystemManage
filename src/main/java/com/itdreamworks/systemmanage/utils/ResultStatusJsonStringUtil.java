package com.itdreamworks.systemmanage.utils;


import com.itdreamworks.systemmanage.entity.enums.ResultStatus;

public class ResultStatusJsonStringUtil {
    public static String getStatusString(ResultStatus status){
        return String.format("{\"status\":%d,\"message\":\"%s\"}",status.getValue(),status.getDescription());
    }

    public static String getStatusString(ResultStatus status,String keysValues){
        return String.format("{\"status\":%d,\"message\":\"%s\",%s}",
                status.getValue(),
                status.getDescription(),
                keysValues);
    }

}
