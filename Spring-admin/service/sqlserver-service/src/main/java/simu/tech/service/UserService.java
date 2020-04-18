package simu.tech.service;

import simu.tech.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    int addUser(User user);

    int deleteUser(String userName);
}
