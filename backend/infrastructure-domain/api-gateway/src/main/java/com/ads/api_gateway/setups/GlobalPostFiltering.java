package com.ads.api_gateway.setups;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GlobalPostFiltering implements GlobalFilter, Ordered {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GlobalPostFiltering.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // chain.filter(exchange) ejecuta el resto de la cadena.
        // .then(Mono.fromRunnable(...)) se ejecuta al final, después de recibir la respuesta.
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("<<<< [POST FILTER - 3] Respuesta saliente. Status: {}", 
                     exchange.getResponse().getStatusCode());
        }));
    }

    @Override
    public int getOrder() {
        // Baja prioridad, se ejecuta al final de los filtros globales
        return 10; 
    }
}