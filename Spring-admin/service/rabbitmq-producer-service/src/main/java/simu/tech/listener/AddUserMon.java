package simu.tech.listener;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import simu.tech.config.RabbitMQConfig;
import simu.tech.pojo.Task;
import simu.tech.service.PmService;

@Component
public class AddUserMon {

    @Autowired
    private PmService pmService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = RabbitMQConfig.CG_ADDUSER)
    public void receiveAddPointMessage(String message){
        System.out.println("接收到消息");

        Task task = JSON.parseObject(message, Task.class);

        if (task == null || StringUtils.isEmpty(task.getRequestBody())){
            return;
        }

        //判断redis中是否存在内容
        Object value = redisTemplate.boundValueOps(task.getId()).get();
        if (value != null){
            return;
        }

        //更新pm表的数据
        int result = pmService.updateUserMon(task);
        if (result<=0){
            return;
        }

        //返回通知
        rabbitTemplate.convertAndSend(RabbitMQConfig.EX_ADDUSER,RabbitMQConfig.CG_FINISHADDUSER_KEY,JSON.toJSONString(task));
        System.out.println("操作成功,返回消息");

    }
}
