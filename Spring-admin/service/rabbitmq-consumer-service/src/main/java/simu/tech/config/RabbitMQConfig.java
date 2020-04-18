package simu.tech.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列
 */
@Configuration
public class RabbitMQConfig {

    //pm的消息队列
//    public static final String SIMU_PM="simu_pm";
//    @Bean
//    public Queue queue(){
//        return  new Queue(SIMU_PM);
//    }


    //交换机名
    public static final String EX_ADDUSER = "ex_adduser";

    //调用方发送消息的消息队列
    public static final String CG_ADDUSER = "cg_adduser";

    //调用方接收消息的消息队列
    public static final String CG_FINISHADDUSER = "cg_finishadduser";

    //调用方发送消息的路由key
    public static final String CG_ADDUSER_KEY = "adduser";

    //调用方接收消息的路由key
    public static final String CG_FINISHADDUSER_KEY = "finishadduser";

    /**
     * 交换机配置
     * @return the exchange
     */
    @Bean(EX_ADDUSER)
    public Exchange EX_ADDUSER() {
        return ExchangeBuilder.directExchange(EX_ADDUSER).durable(true).build();
    }
    //声明调用方接收消息的消息队列
    @Bean(CG_FINISHADDUSER)
    public Queue QUEUE_CG_FINISHADDUSER() {
        Queue queue = new Queue(CG_FINISHADDUSER);
        return queue;
    }
    //声明调用方发送消息的消息队列
    @Bean(CG_ADDUSER)
    public Queue QUEUE_CG_ADDUSER() {
        Queue queue = new Queue(CG_ADDUSER);
        return queue;
    }
    /**
     * 绑定队列到交换机 .
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    //调用方接收消息
    @Bean
    public Binding BINDING_QUEUE_FINISHADDUSER(@Qualifier(CG_FINISHADDUSER) Queue queue, @Qualifier(EX_ADDUSER) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CG_FINISHADDUSER_KEY).noargs();
    }
    //调用方发送消息
    @Bean
    public Binding BINDING_QUEUE_ADDUSER(@Qualifier(CG_ADDUSER) Queue queue, @Qualifier(EX_ADDUSER) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CG_ADDUSER_KEY).noargs();
    }
}
