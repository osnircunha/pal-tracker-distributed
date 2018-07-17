package io.pivotal.pal.tracker.oauthserver;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configAuthentication = new CorsConfiguration();
        configAuthentication.setAllowCredentials(true);
        configAuthentication.addAllowedOrigin("*");
        configAuthentication.addAllowedHeader("*");
        configAuthentication.addAllowedMethod("POST");
        configAuthentication.addAllowedMethod("GET");
        configAuthentication.addAllowedMethod("DELETE");
        configAuthentication.addAllowedMethod("PUT");
        configAuthentication.addAllowedMethod("OPTIONS");
        configAuthentication.setMaxAge(3600L);
        //source.registerCorsConfiguration("/oauth/token", configAuthentication);
        source.registerCorsConfiguration("/**", configAuthentication); // Global for all paths

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
