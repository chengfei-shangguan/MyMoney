package simu.tech.listener;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simu.tech.config.RabbitMQConfig;
import simu.tech.pojo.Task;
import simu.tech.service.TaskServcie;


@Component
public class DelTaskListener {

    @Autowired
    private TaskServcie taskServcie;


    @RabbitListener(queues = RabbitMQConfig.CG_FINISHADDUSER)
    public void receiveDelTaskMessage(String message) {
        System.out.println("操作完成");

        Task task = JSON.parseObject(message, Task.class);

        //删除任务表中的数据
        taskServcie.delTask(task);
    }
}
