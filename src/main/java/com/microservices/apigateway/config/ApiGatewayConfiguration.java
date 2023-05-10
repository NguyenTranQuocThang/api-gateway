package com.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
//                .route(p -> p.path("/client00/**").uri("lb://client00"))
                .route(p -> p.path("/client01/**")
                        .filters(f -> f.rewritePath("/client01/(?<segment>.*)","/${segment}"))
                        .uri("lb://client01"))
//                .route(p -> p.path("/client00/**")
//                        .filters(
//                                f -> f.rewritePath(
//                                        "/client00-new/(?<segment>.*)",
//                                        "/client00-feign/${segment}"
//                                )
//                        ).uri("lb://client00")
//                )
                .route(p -> p.path("/client00/**")
                        .filters(f -> f.rewritePath("/client00/(?<segment>.*)","/${segment}"))
                        .uri("lb://client00"))
                .build();
    }
}
