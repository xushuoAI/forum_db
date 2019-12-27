package org.dgut.community.interceptor;

import org.dgut.community.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by limi on 2017/10/15.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
//            response.sendRedirect("/admin");
            User user = (User) request.getSession().getAttribute("user");
            System.out.println(user.getUserName());
            return false;
        }
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getUserName());
        return true;
    }
}
