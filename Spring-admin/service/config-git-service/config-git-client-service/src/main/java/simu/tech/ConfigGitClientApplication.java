package simu.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigGitClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigGitClientApplication.class, args);
    }
}
