package simu.tech.dao;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import simu.tech.pojo.PointLog;
import tk.mybatis.mapper.common.Mapper;

public interface PointLogMapper extends Mapper<PointLog> {
    @Select("select * from tb_point_log where order_id = #{orderId}")
    PointLog findPointLogByOrderId(@Param("orderId") String orderId);
}
