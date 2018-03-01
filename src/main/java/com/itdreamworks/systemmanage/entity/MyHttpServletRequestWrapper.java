package com.itdreamworks.systemmanage.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private Map<String,String[]> mapper;

    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        mapper = new HashMap<>();
        mapper.putAll(request.getParameterMap());
    }

    @Override
    public String getParameter(String name) {
        String[] values = mapper.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return mapper.get(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return new Enumeration<String>() {
            private Iterator<String> iterator = mapper.keySet().iterator();

            @Override
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            @Override
            public String nextElement() {
                return iterator.next();
            }
        };
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return mapper;
    }

    public void put(String key, String[] value) {
        mapper.put(key, value);
    }
}
