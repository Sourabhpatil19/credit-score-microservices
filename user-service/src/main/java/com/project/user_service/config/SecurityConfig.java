package com.project.user_service.config;

import com.project.user_service.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtFilter jwtFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // PUBLIC APIs
                        .requestMatchers(
                                "/auth/login",
                                "/auth/refresh",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // PUBLIC USER CREATION
                        .requestMatchers(
                                HttpMethod.POST,
                                "/users"
                        ).permitAll()

                        // ADMIN ONLY
                        .requestMatchers("/users/all")
                        .hasRole("ADMIN")

                        // OFFICER + ADMIN
                        .requestMatchers(
                                HttpMethod.GET,
                                "/users/*"
                        ).hasAnyRole("OFFICER", "ADMIN")

                        // ALL OTHER USER APIs -> ADMIN
                        .requestMatchers("/users/**")
                        .hasRole("ADMIN")

                        // EVERYTHING ELSE
                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
