package simu.tech.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {


    /**
     * 通过@Value 来讲配置文件中的值写入到代码中,
     * clientApplication客户端启动时候获取分支上的配置参数${from}时候,配置中心会从git仓库拉取colo-dev.properties,colo.properties等文件到本地
     * 例如:Adding property source: file:/C:/Users/600336/AppData/Local/Temp/config-repo-1543229677936769440/spring-cloud-config-file/colo-dev.properties
     */
    @Value("${from}")
    private String from;

    //http://localhost:7003/from
    @RequestMapping("/from")
    public String from() {
        return from;
    }
}
