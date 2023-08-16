package com.wanted.board.global.config;

import com.wanted.board.domain.user.application.UserService;
import com.wanted.board.domain.user.dao.UserRepository;
import com.wanted.board.global.config.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/posts/createPost").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(null)
                .and()
                .addFilterBefore(new JwtFilter(userRepository, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

