package simu.tech.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import simu.tech.service.AuthService;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthService authService;

    public static final String Authorization = "authorization";

    private static final String Login_URL = "http://localhost:8001/api/oauth/toLogin";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //判断当前请求是否为登录请求,如果是登录请求,则放行
        String path = request.getURI().getPath();
        if ("/api/oauth/login".equals(path)||!URLFilter.hasAuthorize(path)){
            //放行
            return chain.filter(exchange);
        }
        //如果不是登录请求,获取请求中的Cookie的值,如果该值不存在,拒绝本次访问
        String jti = authService.getJtiFromCookie(request);
        if (StringUtils.isEmpty(jti)){
            //拒绝访问
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
////            return response.setComplete();
            //跳转登录页面
            return this.toLoginPage(Login_URL+"?FROM="+request.getURI().getPath(),exchange);
        }
        //通过Cookie中的值获取Redis中的值,如果该值不存在,拒绝本次访问
        String jwt = authService.getJwtFromRedis(jti);
        if (StringUtils.isEmpty(jwt)){
            //拒绝访问
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
            //跳转登录页面
            return this.toLoginPage(Login_URL+"?FROM="+request.getURI(),exchange);
        }
        //对当前请求进行增强,让他会携带令牌的信息
        request.mutate().header(Authorization,"Bearer "+jwt);
        return chain.filter(exchange);
    }

    //跳转登录页面
    private Mono<Void> toLoginPage(String loginUrl, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location",loginUrl);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }


}
