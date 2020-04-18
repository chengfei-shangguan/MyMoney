package simu.tech.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simu.tech.dao.PmDao;
import simu.tech.dao.TaskLogDao;
import simu.tech.pojo.Task;
import simu.tech.pojo.TaskLog;
import simu.tech.service.PmService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class PmServiceImpl implements PmService {

    @Autowired
    private PmDao pmDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TaskLogDao taskLogDao;

    @Override
    public void updateUser(Integer addId, Integer redId, Integer mon) {
        pmDao.addUser(addId, mon);
        int i = 1/ 0;
        pmDao.redUser(redId, mon);
    }

    @Override
    public int updateUserMon(Task task) {
        //先去查询数据库,看之前是否有这个任务的消费历史
        TaskLog taskLog = taskLogDao.selectByPrimaryKey(task.getId());
        if (taskLog!=null){
            return 0;
        }

        //将任务存入redis
        redisTemplate.boundValueOps(task.getId()).set("exist",30, TimeUnit.SECONDS);
        Map map = JSON.parseObject(task.getRequestBody(), Map.class);
        Integer addId = (Integer) map.get("addId");
        Integer redId = (Integer) map.get("redId");
        Integer mon = (Integer) map.get("mon");

        //执行操作
        pmDao.addUser(addId, mon);
        pmDao.redUser(redId, mon);
        //如果执行成功了,将任务给数据库中存储一份

        //bean复制
        TaskLog taskLog1 = new TaskLog();
        BeanUtils.copyProperties(task, taskLog1);
        //记录任务信息
        taskLogDao.insertSelective(taskLog1);

        //删除redis中的记录
        redisTemplate.delete(task.getId());


        return 1;
    }
}
