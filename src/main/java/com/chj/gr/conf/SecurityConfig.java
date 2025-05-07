package com.chj.gr.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            .pathMatchers("/actuator/**").permitAll()
            
            .pathMatchers("/ms1/api/public/**").permitAll()
            .pathMatchers("/ms2/api/public/**").permitAll()
            
            .anyExchange().authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt();
        return http.build();
    }
}
