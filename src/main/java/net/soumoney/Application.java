package net.soumoney;

import net.soumoney.common.GlobalFilter;
import net.soumoney.common.GlobalInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jiangxiaojie on 2017/3/21.
 */
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean addFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new GlobalFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
