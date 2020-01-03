package org.dgut.community.config;

import org.dgut.community.filter.CorsFilter;
import org.dgut.community.filter.NewsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean CorsFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //注入过滤器
        registrationBean.setFilter(new CorsFilter());
        //过滤器名称
        registrationBean.setName("CorsFilter");
        //拦截规则
        registrationBean.addUrlPatterns("/*");
        //过滤器顺序
        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);

        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean newsFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new NewsFilter());
        registration.addUrlPatterns("/News/*");
        registration.setOrder(2);
        return registration;
    }
}
