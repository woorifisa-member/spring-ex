package com.woorifisa.board.config;

import com.woorifisa.board.argumentresolver.MemberArgumentResolver;
import com.woorifisa.board.interceptor.LogInterceptor;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Interceptor Config");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        log.info("Argument Resolver Config");
        resolvers.add(new MemberArgumentResolver());
    }

}
