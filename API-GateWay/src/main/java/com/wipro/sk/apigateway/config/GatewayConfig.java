package com.wipro.sk.apigateway.config;

import com.wipro.sk.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        // A default filter config that just validates the token without requiring a specific role.
        // Your custom filter logic will handle the token validation for all protected routes.
        JwtAuthenticationFilter.Config defaultConfig = new JwtAuthenticationFilter.Config();

        return builder.routes()
                // 1. Auth Service Route - Publicly accessible for login and signup
                .route("user-auth-service-route", r -> r.path("/api/auth/**")
                        .uri("lb://USER-AUTHENTICATION-SERVICE"))

                // 2. Customer Service Route - Requires a valid token
                .route("customer-service-route", r -> r.path("/api/customers/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter.apply(defaultConfig)))
                        .uri("lb://BankingCustomerService"))

                // 3. Account Service Route - Requires a valid token
                .route("account-service-route", r -> r.path("/api/accounts/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter.apply(defaultConfig)))
                        .uri("lb://BankingAccountService"))

                // 4. Payment Service Route - Requires a valid token
                .route("payment-service-route", r -> r.path("/api/payments/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter.apply(defaultConfig)))
                        .uri("lb://BankingPaymentService"))

                // 5. Audit Service Route - Requires a valid token
                .route("audit-service-route", r -> r.path("/api/audits/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter.apply(defaultConfig)))
                        .uri("lb://BankingAuditService"))
                
                // 6. Notification Service Route - Requires a valid token
                .route("notification-service-route", r -> r.path("/api/notifications/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter.apply(defaultConfig)))
                        .uri("lb://BankingNotificationService"))
                .build();
    }
}

