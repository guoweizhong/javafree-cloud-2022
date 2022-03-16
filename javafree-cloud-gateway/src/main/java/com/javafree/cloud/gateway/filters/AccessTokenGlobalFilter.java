package com.javafree.cloud.gateway.filters;
/*
 * @Description:全局过滤器，用于统一鉴权
 * @Author gwz  gwz126@126.com
 * @Date 2021/9/10 8:17
 * @version V1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Slf4j
@Component
public class AccessTokenGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        //调用chain.filter 继续向下游执行
        return chain.filter(exchange);
    }

    //标识当前过滤器的优先级，值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
