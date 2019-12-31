package org.dgut.community.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**

 允许跨域访问过滤器
 */
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;


        String []  allowDomain= {"http://127.0.0.1","http://localhost","http://meilejushi.club"};
        Set<String> allowedOrigins= new HashSet<String>(Arrays.asList(allowDomain));
        String originHeader=((HttpServletRequest) httpServletRequest).getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", originHeader);
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "content-type, x-requested-with");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        }

        if (((HttpServletRequest) httpServletRequest).getMethod().equals("OPTIONS")) {
            httpServletResponse.getWriter().println("ok");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}