package simu.tech.task;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import simu.tech.config.RabbitMQConfig;
import simu.tech.dao.TaskDao;
import simu.tech.pojo.Task;

import java.util.Date;
import java.util.List;

/**
 * 定时任务,获取当前任务表中的未完成的任务,并且发送到消息队列
 */
@Component
public class QueryTask {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TaskDao taskDao;

    @Scheduled(cron = "0/2 * * * * ?")//每隔两秒执行一次
    public void queryTask(){

        //1.获取小于系统当前时间数据
        List<Task> taskList = taskDao.findTaskLessTanCurrentTime(new Date());

        if (taskList!=null && taskList.size()>0){
            //将任务数据发送到消息队列
            for (Task task : taskList) {
                rabbitTemplate.convertAndSend(RabbitMQConfig.EX_ADDUSER,RabbitMQConfig.CG_ADDUSER_KEY, JSON.toJSONString(task));
                System.out.println("发送任务");
            }
        }
    }
}
