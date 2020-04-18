package simu.tech.Test;

import simu.tech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Demo {

    @Autowired
    private UserService userService;
    public void demo(){
        Date date = new Date();
        userService.insert(date,null);
    }

}
