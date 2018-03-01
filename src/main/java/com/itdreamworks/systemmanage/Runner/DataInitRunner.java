package com.itdreamworks.systemmanage.Runner;

import com.itdreamworks.systemmanage.config.ApiRequestMapConfig;
import com.itdreamworks.systemmanage.config.CustomerDataManageMap;
import com.itdreamworks.systemmanage.config.DataManageMap;
import com.itdreamworks.systemmanage.config.DmRequestMapConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitRunner implements ApplicationRunner {
    @Autowired
    private DataManageMap dmap;
    @Autowired
    private CustomerDataManageMap cdmap;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        DmRequestMapConfig.setRequestRouteMap(dmap);
        ApiRequestMapConfig.setRequestRouteMap(cdmap);
    }
}
