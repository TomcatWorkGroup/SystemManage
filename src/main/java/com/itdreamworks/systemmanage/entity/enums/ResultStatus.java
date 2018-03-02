package com.itdreamworks.systemmanage.entity.enums;

public enum ResultStatus {
    SUCCESS("操作成功",0),
    ERROR_PASSPORT("用户名或密码错误",500),
    ERROR_AUTHORIZATION("未经授权的API调用",501),
    ERROR_API_NULL("被调用的API不存在",502);


    private String description;
    private int index;
    private ResultStatus(String description, int index){
        this.description = description;
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public int getValue(){
        return this.index;
    }
}
