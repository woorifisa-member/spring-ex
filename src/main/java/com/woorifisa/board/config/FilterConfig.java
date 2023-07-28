package com.woorifisa.board.config;

import com.woorifisa.board.filter.Utf8EncodingFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Utf8EncodingFilter> encodingFilter() {

        log.info("Filter Config");

        FilterRegistrationBean<Utf8EncodingFilter> filterRegistration =
            new FilterRegistrationBean<>(new Utf8EncodingFilter());

        filterRegistration.setOrder(1);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setName("utf8EncodingFilter");

        return filterRegistration;
    }

}
