package cn.qrq.config;

import cn.qrq.springboot.component.LoginHandllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
/*   @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test/login.html").setViewName("/user/login");
    }*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(new LoginHandllerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/login","/test/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }



}
