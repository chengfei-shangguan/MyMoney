package simu.tech.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: yml配置实体
 * @Project: SolrProject
 * @Author: JasonGofen
 * @Date: 2019/3/27
 * @UpdatedBy:
 * @UpdateDate:
 */
@Component
@ConfigurationProperties(prefix = "es")
@Data
public class ESConfigProperties {

    private String server;

    private String core;

    private String dir;

}
