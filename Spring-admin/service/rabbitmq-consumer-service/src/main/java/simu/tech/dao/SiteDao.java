package simu.tech.dao;

import org.apache.ibatis.annotations.Update;
import simu.tech.pojo.User;
import tk.mybatis.mapper.common.Mapper;

public interface SiteDao extends Mapper<User> {

    @Update("update user set money = money + #{mon} where id = #{addId}")
    void addUser(Integer addId, Integer mon);
    @Update("update user set money = money - #{mon} where id = #{redId}")
    void redUser(Integer redId, Integer mon);
}
