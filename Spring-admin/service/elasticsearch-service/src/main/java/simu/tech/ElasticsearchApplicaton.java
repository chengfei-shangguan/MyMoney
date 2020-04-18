package simu.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
public class ElasticsearchApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplicaton.class,args);
    }
}