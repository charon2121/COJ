package com.coj.cojbackend.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用跨站请求伪造
                .cors(withDefaults())  // 启用 CORS（跨域资源共享）withDefaults() 方法表示使用默认的 CORS 配置
                .authorizeHttpRequests(auth -> auth  // 配置 HTTP 请求的授权规则
//                        .requestMatchers("/api/user").hasRole("ADMIN")
//                        .requestMatchers("/api/admin").hasRole("ADMIN")
                        .anyRequest().permitAll()  // 所有其他请求都允许访问（不需要认证）
                )
                .httpBasic(withDefaults());  // 启用 HTTP Basic 认证

        // 构建并返回 SecurityFilterChain 实例
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();

        var admin = User.withUsername("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * spring security 提供的哈希加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
