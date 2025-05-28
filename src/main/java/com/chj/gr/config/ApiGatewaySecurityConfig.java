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
        	/**
        	 * API Gateways typically handle stateless REST APIs, where CSRF protection is unnecessary
        	 * 		because requests are authenticated via tokens (e.g., JWT, OAuth2)
        	 * 		rather than session-based authentication.
        	 * If CSRF is enabled, it expects a CSRF token for state-changing requests,
        	 * 		which may not be provided by clients.
        	 * 
        	 * 
        	 * gr-oauth2-demo ==> gr-resource-consumer-webclient
        	 * 			==> springboot-conf-gateway-api-oauth2
        	 * 			==> springboot-conf-sts-authorization-server-db     ========> CSRF ERROR.
[reactor-http-nio-7] DEBUG org.springframework.security.web.server.util.matcher.AndServerWebExchangeMatcher.method:61 - Trying to match using org.springframework.security.web.server.csrf.CsrfWebFilter$DefaultRequireCsrfProtectionMatcher@6b542c47
[reactor-http-nio-7] DEBUG org.springframework.security.web.server.util.matcher.AndServerWebExchangeMatcher.method:66 - Did not match
[reactor-http-nio-7] DEBUG org.springframework.security.web.server.authentication.AuthenticationWebFilter.method:127 - Authentication failed: Failed to validate the token

			 *
			 * gr-oauth2-demo ==> gr-resource-consumer-webclient
        	 * 			==> springboot-conf-gateway-api-oauth2
        	 * 			==> gr-oauth2-demo ==> gr-auth-server    			========> NOO CSRF ERROR.
        	 */
        	.csrf().disable() // Disable CSRF for stateless APIs
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