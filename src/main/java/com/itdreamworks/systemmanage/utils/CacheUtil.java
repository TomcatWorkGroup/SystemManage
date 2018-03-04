package com.itdreamworks.systemmanage.utils;

import com.itdreamworks.systemmanage.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil {
    public static final String TOKEN_API = "token_api";
    public static final String TOKEN_SYSTEM="token_system";

    @Autowired
    private EhCacheCacheManager cacheManager;


    private Cache getCache(String cacheName){
        return cacheManager.getCache(cacheName);
    }

    public String getToken(String cacheName, String key){
        Cache cache = getCache(cacheName);
        Cache.ValueWrapper element = cache.get(key);
        if(null != element){
            return (String) element.get();
        }
        return null;
    }

    public void putToken(String cacheName, Token token){
        Cache cache = getCache(cacheName);
        cache.put(token.getTokenString(),token.getIdentity());
    }
}
