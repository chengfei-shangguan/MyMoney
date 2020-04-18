package simu.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simu.tech.entity.Result;
import simu.tech.entity.StatusCode;
import simu.tech.service.SiteService;

@RestController
@CrossOrigin
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private SiteService siteService;

    @GetMapping("/update")
    public Result updateUser() {
        Integer addId = 1;
        Integer redId = 2;
        Integer mon = 500;
        try {
            siteService.updateUser(addId,redId,mon);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"操作失败");
        }
        return new Result();
    }
}
