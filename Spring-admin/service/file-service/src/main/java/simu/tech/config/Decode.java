package simu.tech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;

public class Decode {
    @Autowired
    private RedisTemplate redisTemplate;

//    @Value("${myParams.redis.Id}")
//    private String Id;
//    @Value("${myParams.redis.Name}")
//    private String Name;
//
//    public String getId(HttpServletRequest request){
//        String token = request.getHeader("token");
//        String id = (String) redisTemplate.boundValueOps(token+Id).get();
//        return id;
//
//    }
//
//    public String getName(HttpServletRequest request){
//        String token = request.getHeader("token");
//        String name = (String) redisTemplate.boundValueOps(token+Name).get();
//        return name;
//
//    }

}
