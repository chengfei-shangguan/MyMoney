package simu.tech.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import site.tech.entity.Result;

@FeignClient(name = "service-pm")
@Component
public interface PmFeign {

    @GetMapping(value = "/pm/update")
    public Result updateUser();
}
