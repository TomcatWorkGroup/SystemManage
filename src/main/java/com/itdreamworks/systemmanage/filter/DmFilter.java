package com.itdreamworks.systemmanage.filter;

import com.itdreamworks.systemmanage.client.TemplateClient;
import com.itdreamworks.systemmanage.config.FeignSetting;
import com.itdreamworks.systemmanage.config.DmRequestMapConfig;
import com.itdreamworks.systemmanage.utils.WebRequestUtil;
import feign.Feign;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 存在URL泄露后，非法调用的问题
 * 可参考ApiFilter Token验证的处理方式
 */
@WebFilter(filterName = "dmFilter", urlPatterns = "*.dm")
public class DmFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //        System.out.println(request.getRequestURL());// http://localhost:8080/jqueryLearn/resources/request.jsp
//        System.out.println(request.getRequestURI());///jqueryLearn/resources/request.jsp
//        System.out.println(request.getContextPath());///jqueryLearn
//        System.out.println(request.getServletPath());//resources/request.jsp

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (DmRequestMapConfig.getRequestRouteMap().containsKey(request.getServletPath())) {
            servletResponse.setContentType("application/json; charset=utf-8");
            FeignSetting setting = DmRequestMapConfig.getRequestRouteMap().get(request.getServletPath());
            TemplateClient client =//.encoder(new JacksonEncoder())
                    Feign.builder().target(TemplateClient.class, setting.getUrl());

            if (FeignSetting.METHOD_GET.equals(setting.getMethod())) {
                String content = "";
                if (setting.hasParameters()) {
                    content = client.get(WebRequestUtil.getRequestParameterMap(request));
                } else {
                    content = client.get();
                }
                servletResponse.getWriter().write(content);
            } else if (FeignSetting.METHOD_POST.equals(setting.getMethod())) {
                String content = "";
                if (setting.hasParameters()) {
                    content = client.post(WebRequestUtil.getRequestParameterMap(request));
                } else {
                    content = client.post();
                }
                servletResponse.getWriter().write(content);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
