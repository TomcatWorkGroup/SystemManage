package com.itdreamworks.systemmanage.filter;

import com.itdreamworks.systemmanage.client.TemplateClient;
import com.itdreamworks.systemmanage.config.FeignSetting;
import com.itdreamworks.systemmanage.config.RequestMapConfig;
import com.itdreamworks.systemmanage.utils.WebRequestUtil;
import feign.Feign;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "apiFilter", urlPatterns = "*.api")
public class ApiFilter implements Filter {
    public static final String USER_TOKEN_NAME = "usr_token";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(USER_TOKEN_NAME);
        servletResponse.setContentType("application/json; charset=utf-8");
        String content ="";
        if(USER_TOKEN_NAME.equals(token)){
            content = "{\"code\":1,\"msg\":\"ok\"}";
        }else{
            content = "{\"code\":0,\"msg\":\"error\"}";
        }
        servletResponse.getWriter().write(content);
    }

    @Override
    public void destroy() {

    }
}
