package simu.tech.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import simu.tech.entity.Result;
import simu.tech.pojo.User;

@FeignClient(name = "user")
public interface UserFeign {

    @GetMapping(value = "/user/load/{username}")
    public User findUseInfo(@PathVariable("username") String username) ;

    @GetMapping(value = "/user/points/add")
    public Result addUserPoints(@RequestParam(value = "points") Integer points);
}
