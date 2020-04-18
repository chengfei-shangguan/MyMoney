package simu.tech.dao;

import org.apache.ibatis.annotations.Mapper;
import simu.tech.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    int addUser( User user );
    int deleteUser(String userName);
}
