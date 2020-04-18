package simu.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import simu.tech.config.TokenDecode;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"simu.tech.dao"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run( UserApplication.class);
    }

//    /**
//     * 解析用户令牌对象
//     */
//    @Bean
//    public TokenDecode getTokenDecode(){
//        return new TokenDecode();
//    }
}
