package com.chj.gr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ApiGatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            .pathMatchers("/ms1/gr-ms1-resource/public/**").permitAll()
            .pathMatchers("/ms2/gr-ms2-resource/public/**").permitAll()
            .pathMatchers("/ms3/gr-ms3-resource/public/**").permitAll()
            
            .pathMatchers("/ms4/gr-oauth2-swagger-ms1/public/**").permitAll()
            .pathMatchers("/ms5/gr-oauth2-swagger-ms2/public/**").permitAll()
            
            .pathMatchers("/ms6/z-springboot-bar-service/**").permitAll()
            .pathMatchers("/ms7/z-springboot-foo-service/**").permitAll()
            .pathMatchers("/ms8/z-springboot-hello/**").permitAll()
            .pathMatchers("/ms9/z-springboot-hello-world-service/**").permitAll()
            
            .pathMatchers("/ms10/sts-oauth2-client1-resource/public/**").permitAll()
            .pathMatchers("/ms11/sts-oauth2-client2-resource/public/**").permitAll()
            .pathMatchers("/ms12/sts-oauth2-products-resource/public/**").permitAll()
            
            .pathMatchers("/actuator/**").permitAll()
            .anyExchange().authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt();
        return http.build();
    }
}