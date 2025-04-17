package com.groupI.fitness.config;

import com.groupI.fitness.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DemoWebConfig implements WebMvcConfigurer {


    /**
     * interceptor configuration
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Add interceptor
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**")
                // Exclude path for interceptor
                .excludePathPatterns("/api/loginUser", "/api/loginStaff", "/api/user/**", "/api/menu/**","/api/cart/**","/api/course/**","/api/mycourse/**","/api/plan/**","/api/favorite/**");
    }


}
