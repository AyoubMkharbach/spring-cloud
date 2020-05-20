package com.ayoub.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    RouteLocator staticRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(r -> r
                        .path("/countries/**")
                        .filters(filter -> filter
                                .addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key", "5d7bdc18a3msh3c41d14f5964b33p150813jsnd75b01e6e334")
                                .rewritePath("/countries/(?<segment>.*)", "/${segment}")
                                .hystrix(config -> config.setName("countries").setFallbackUri("forward:/defaultCountries"))
                        )
                        .uri("https://restcountries-v1.p.rapidapi.com")
                        .id("r1"))
                .route(r -> r
                        .path("/muslim/**")
                        .filters(filter -> filter
                                .addRequestHeader("x-rapidapi-host", "muslimsalat.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key", "5d7bdc18a3msh3c41d14f5964b33p150813jsnd75b01e6e334")
                                .rewritePath("/muslim/(?<segment>.*)", "/${segment}")
                        )
                        .uri("https://muslimsalat.p.rapidapi.com")
                        .id("r2"))
                .build();
    }

    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }
}
