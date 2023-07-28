package com.woorifisa.board.config;

import com.woorifisa.board.common.security.BcryptPasswordEncoder;
import com.woorifisa.board.common.security.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BcryptPasswordEncoder();
    }

}
