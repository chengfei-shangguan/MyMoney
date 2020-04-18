package simu.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simu.tech.entity.Result;
import simu.tech.entity.StatusCode;
import simu.tech.service.PmService;

@RestController
@CrossOrigin
@RequestMapping("/pm")
public class PmController {

    @Autowired
    private PmService pmService;

    @RequestMapping("/update")
    public Result updateUser() {
        Integer addId = 1;
        Integer redId = 2;
        Integer mon = 500;
        try {
            pmService.updateUser(addId,redId,mon);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"操作失败");
        }
        return new Result();
    }

}
