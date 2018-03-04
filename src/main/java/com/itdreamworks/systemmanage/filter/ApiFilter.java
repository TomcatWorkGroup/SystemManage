package com.itdreamworks.systemmanage.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itdreamworks.systemmanage.client.TemplateClient;
import com.itdreamworks.systemmanage.config.ApiRequestMapConfig;
import com.itdreamworks.systemmanage.config.FeignSetting;
import com.itdreamworks.systemmanage.entity.MyHttpServletRequestWrapper;
import com.itdreamworks.systemmanage.entity.Token;
import com.itdreamworks.systemmanage.entity.enums.ResultStatus;
import com.itdreamworks.systemmanage.utils.CacheUtil;
import com.itdreamworks.systemmanage.utils.ResultStatusJsonStringUtil;
import com.itdreamworks.systemmanage.utils.WebRequestUtil;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiFilter implements Filter {
    public static final String USER_TOKEN_NAME = "usr_token";

    @Value("${customerdatamanage.enter}")
    private String enterUrl ;//="/account/signin.api";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        cacheUtil = new CacheUtil();
//        mapper = new ObjectMapper();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String tokenKey = request.getHeader(USER_TOKEN_NAME);
        response.setContentType("application/json; charset=utf-8");

        if (null != tokenKey) {
            String orgId = cacheUtil.getToken(CacheUtil.TOKEN_API, tokenKey);
            if (null != orgId) {
                if (ApiRequestMapConfig.getRequestRouteMap().containsKey(request.getServletPath())) {
                    //重新包装WebRequest
                    MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(request);
                    //添加orgId参数及值
                    requestWrapper.put("customerId",new String[]{orgId});
                    response.getWriter().write(
                            handleRequest(requestWrapper));
                } else {
                    //out404Msg(response);
                    response.getWriter().write(ResultStatusJsonStringUtil.getStatusString(ResultStatus.ERROR_API_NULL));
                }
            } else {
                //out401Msg(response);
                response.getWriter().write(
                        ResultStatusJsonStringUtil.getStatusString(
                                ResultStatus.ERROR_AUTHORIZATION,
                                String.format("\"enterUrl\":\"%s\"",enterUrl)));
            }
        } else {
            if (enterUrl.equals(request.getServletPath())) {
                String str = handleRequest(request);
                if(str.isEmpty()){
                    //out204Msg(response);
                    response.getWriter().write(ResultStatusJsonStringUtil.getStatusString(ResultStatus.ERROR_PASSPORT));
                }else{
                    LinkedHashMap jsonObj = (LinkedHashMap) mapper.readValue(str, Object.class);
                    if(!checkPassword(request.getParameter("password"),jsonObj.get("password"))){
                        //out204Msg(response);
                        response.getWriter().write(ResultStatusJsonStringUtil.getStatusString(ResultStatus.ERROR_PASSPORT));
                    }else{
                        Token token = Token.getInstance(jsonObj.get("orgId").toString());
                        cacheUtil.putToken(CacheUtil.TOKEN_API,token);
                        servletResponse.setContentType("text/plain; charset=utf-8");
                        response.getWriter().write(token.getTokenString());
                    }
                }
            } else {
                //out401Msg(response);
                response.getWriter().write(
                        ResultStatusJsonStringUtil.getStatusString(
                                ResultStatus.ERROR_AUTHORIZATION,
                                String.format("\"enterUrl\":\"%s\"",enterUrl)));
            }
        }
    }

    @Override
    public void destroy() {

    }

    private String handleRequest(HttpServletRequest request) {
        String content = "";
        FeignSetting setting = ApiRequestMapConfig.getRequestRouteMap().get(request.getServletPath());
        TemplateClient client =
                Feign.builder().target(TemplateClient.class, setting.getUrl());

        if (FeignSetting.METHOD_GET.equals(setting.getMethod())) {
            if (setting.hasParameters()) {
                content = client.get(WebRequestUtil.getRequestParameterMap(request));
            } else {
                content = client.get();
            }
        } else {
            if (setting.hasParameters()) {
                content = client.post(WebRequestUtil.getRequestParameterMap(request));
            } else {
                content = client.post();
            }
        }
        return content;
    }

    private boolean checkPassword(String inputPassword,Object password){
        if(null == inputPassword || inputPassword.isEmpty())
            return false;
        return inputPassword.equals(password);
    }

    /**
     * 在处理完WebRequest参数后，为Map增加Key-Value对
     * 该处理方式避免了对WebRequest的重新包装，但增加了重复性判断
     * @param request
     * @return
     */
    private Map<String,String> handleRequestParameterMap(HttpServletRequest request){
        Map<String,String> map = WebRequestUtil.getRequestParameterMap(request);
        String tokenKey = request.getHeader(USER_TOKEN_NAME);
        if (null == tokenKey) {
            return map;
        }
        String orgId = cacheUtil.getToken(CacheUtil.TOKEN_API, tokenKey);
        map.put("orgId",orgId);
        return map;
    }

    private void out204Msg(HttpServletResponse response) throws IOException {
        response.getWriter().write(ResultStatusJsonStringUtil.getStatusString(ResultStatus.ERROR_PASSPORT));
    }

    private void out401Msg(HttpServletResponse response) throws IOException {
        response.getWriter().write(
                ResultStatusJsonStringUtil.getStatusString(
                        ResultStatus.ERROR_AUTHORIZATION,
                        String.format("\"enterUrl\":\"%s\"",enterUrl)));
    }

    private void out404Msg(HttpServletResponse response) throws IOException {
        //response.setStatus(401);
        response.getWriter().write(ResultStatusJsonStringUtil.getStatusString(ResultStatus.ERROR_API_NULL));
    }
}