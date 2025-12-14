package com.ruoyi.asset.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS Configuration for Frontend Integration
 * Allows cross-origin requests from Vue dev server
 *
 * @author ruoyi
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // Allow all origins in development (restrict in production)
        config.addAllowedOriginPattern("*");
        // Allow all headers
        config.addAllowedHeader("*");
        // Allow all HTTP methods
        config.addAllowedMethod("*");
        // Allow credentials (cookies, authorization headers)
        config.setAllowCredentials(true);
        // Cache preflight response for 1 hour
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
