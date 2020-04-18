package simu.tech.dao;

import org.apache.ibatis.annotations.Param;
import simu.tech.log.Record;

public interface RecordMapper {

    //添加日志
    int insertSelective(Record record);

    //查询是否有这个表
    Integer existTable(String tableName);


    //创建表
    void createNewTable(@Param("tanleName") String tableName);
}
