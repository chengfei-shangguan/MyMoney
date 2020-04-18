package simu.tech.service;

import javax.servlet.http.HttpSession;
import java.util.Date;

public interface UserService {

    void insert(Date date, HttpSession session);
}
