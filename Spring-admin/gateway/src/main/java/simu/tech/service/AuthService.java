package simu.tech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //获取Cookie中的jti的值
    public String getJtiFromCookie(ServerHttpRequest request) {
        HttpCookie httpCookie = request.getCookies().getFirst("uid");
        if (httpCookie!=null){
            //cookie存在
            String jti = httpCookie.getValue();
            return jti;
        }
        return null;
    }

    //根据jti获取Redis中的Jwt令牌
    public String getJwtFromRedis(String jti) {
        // String jwt = stringRedisTemplate.boundValueOps(jti).get();
        String jwt = stringRedisTemplate.opsForValue().get(jti);
        if (jwt!=null){
            //jwt存在
            return jwt;
        }
        return null;
    }
}
