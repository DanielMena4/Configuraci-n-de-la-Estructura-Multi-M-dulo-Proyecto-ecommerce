package com.ads.api_gateway.setups;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFiltering implements GlobalFilter, Ordered {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthenticationFiltering.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Este filtro simula el paso de la validación
        log.info(">>>> [AUTH FILTER - 2] Petición pasó por la capa de Autenticación/Autorización.");

        // Aquí en el futuro se implementaría la validación de JWT, roles, etc.

        return chain.filter(exchange); // Continúa al enrutamiento o PostFiltering
    }

    @Override
    public int getOrder() {
        // Se ejecuta después del filtro Pre.
        return -5; 
    }
}