package com.itdreamworks.systemmanage.filter;

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
            content = "{\"code\":200,\"msg\":\"ok\"}";
            //转发请求
        }else{
            content = "{\"code\":401,\"msg\":\"无法识别API调用者，请进行身份认证！\"}";
        }
        servletResponse.getWriter().write(content);
    }

    @Override
    public void destroy() {

    }
}
