package simu.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description: ${todo}
 * @param ${tags} 
 * @return ${return_type} 
 * @throws
 * @author shangchengfei
 * @date 2020/3/21 00:11 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling//开启定时任务
@MapperScan(basePackages = {"simu.tech.dao"})
@EnableFeignClients(basePackages = {"simu.tech.feign"})
public class RabbitMQConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQConsumerApplication.class, args);
    }
}
