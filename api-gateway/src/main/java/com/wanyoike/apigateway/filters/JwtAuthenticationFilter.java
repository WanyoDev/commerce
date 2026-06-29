package com.wanyoike.apigateway.filters;

import com.wanyoike.apigateway.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange
                .getRequest()
                .getURI()
                .getPath();

        // Allow auth endpoints
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        //Allow public endpoints to bypass JWT validation
        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }
        
        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {

            if (!jwtService.isTokenValid(token)) {

                exchange.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            //Extract Roles and Subject
            Claims claims = jwtService.extractClaimsJWT(token);

            String email = claims.getSubject();

            List<String> roles = claims.get("roles", List.class);

            ServerHttpRequest httpRequest =
                    exchange.getRequest()
                            .mutate()
                            .header("X-User-Email", email)
                            .header("X-User-Roles", String.join(",", roles))
                            .build();

            return chain.filter(exchange
                    .mutate()
                    .request(httpRequest)
                    .build()
            );

        } catch (Exception e) {

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    //Implement in the filter instead of in the Security Configuration class
    private boolean isPublicPath(String path) {
        return path.startsWith("/auth")
                || path.startsWith("/commerce/products")
                || path.equals("/commerce/categories")
                || path.equals("/commerce/brands");
    }

    @Override
    public int getOrder() {
        return -1;
    }
}