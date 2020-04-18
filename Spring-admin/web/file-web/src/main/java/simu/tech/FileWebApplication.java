package simu.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import simu.tech.interceptor.FeignInterceptor;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"simu.tech.feign"})
public class FileWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileWebApplication.class,args);
    }

//    @Bean
//    public FeignInterceptor feignInterceptor(){
//        return  new FeignInterceptor();
//    }
}
