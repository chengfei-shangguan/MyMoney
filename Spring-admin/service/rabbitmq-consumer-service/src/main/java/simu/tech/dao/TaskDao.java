package simu.tech.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import simu.tech.pojo.Task;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface TaskDao extends Mapper<Task> {

    @Select("SELECT * from task WHERE update_time<#{currentTime}")
    @Results({@Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "delete_time",property = "deleteTime"),
            @Result(column = "task_type",property = "taskType"),
            @Result(column = "mq_exchange",property = "mqExchange"),
            @Result(column = "mq_routingkey",property = "mqRoutingkey"),
            @Result(column = "request_body",property = "requestBody"),
            @Result(column = "status",property = "status"),
            @Result(column = "errormsg",property = "errormsg")})
    List<Task> findTaskLessTanCurrentTime(Date date);
}
