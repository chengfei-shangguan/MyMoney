package simu.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import simu.tech.pojo.User;
import simu.tech.service.UserService;

import java.util.List;

@RestController
@RequestMapping("simuuser")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/addUser")
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping(value = "/deleteUser/{userName}")
    public int deleteUser(@PathVariable("userName") String userName) {
        return userService.deleteUser(userName);
    }
}