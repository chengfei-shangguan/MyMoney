package simu.tech.service.impl;

import simu.tech.service.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void insert(Date date, HttpSession session) {
        System.out.println(date.getTime());

    }
}
