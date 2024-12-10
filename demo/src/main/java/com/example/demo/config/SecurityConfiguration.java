package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.filters.JWTFilter;
import com.example.demo.services.JWTService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Autowired
    JWTService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user").permitAll()
                .requestMatchers("/auth").permitAll()
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
            .cors(config -> {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("http://localhost:3000");
                configuration.addAllowedOrigin("http://127.0.0.1:3000");
                configuration.addAllowedHeader("Authorization");
                configuration.addAllowedHeader("Content-Type");
                configuration.addAllowedMethod("GET");
                configuration.addAllowedMethod("POST");
                configuration.addAllowedMethod("PUT");
                configuration.addAllowedMethod("DELETE");
                configuration.setAllowCredentials(true);
        
                // Registro da configuração CORS
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                config.configurationSource(source);
            })
            .build();
    }

}