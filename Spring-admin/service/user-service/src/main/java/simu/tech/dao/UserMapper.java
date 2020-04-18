package simu.tech.dao;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import simu.tech.pojo.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    /**
     * 增加用户积分
     * @param username
     * @param point
     */
    @Update("update tb_user set points = points+#{point} where username = #{username}")
    public int addUserPoints(@Param("username") String username, @Param("point") Integer point);


}
