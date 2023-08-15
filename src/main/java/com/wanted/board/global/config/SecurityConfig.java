package com.wanted.board.global.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .formLogin().disable();

        return httpSecurity
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                )
                //.httpBasic(Customizer.withDefaults()) Jwt 사용할 rest api 방식이면 해줄필요없음
                .build();
    }
}
