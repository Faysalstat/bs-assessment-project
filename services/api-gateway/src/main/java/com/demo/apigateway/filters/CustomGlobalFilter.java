package com.demo.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomGlobalFilter extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            // Log request details
            System.out.println("Request Received - Method: " + exchange.getRequest().getMethod()
                    + ", URI: " + exchange.getRequest().getURI()+ ", Path: " + exchange.getRequest().getPath());

            // Continue the filter chain
            return chain.filter(exchange);
        };
    }

    @Override
    public String name(){
        return "GlobalFilter";
    }
}
