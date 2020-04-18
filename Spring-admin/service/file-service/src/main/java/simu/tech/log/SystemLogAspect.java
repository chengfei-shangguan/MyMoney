package simu.tech.log;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import simu.tech.config.Decode;
import simu.tech.dao.RecordMapper;

@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private Decode decode;

//    @Value("${myParams.redis.Id}")
//    private String Id;
//    @Value("${myParams.log.PC}")
//    private String PC;
//    @Value("${myParams.log.Mobile}")
//    private String Mobile;
//    @Value("${myParams.log.OK}")
//    private String OK;
//    @Value("${myParams.log.ERROR}")
//    private String ERROR;
//    @Value("${myParams.TABLENAME}")
//    private String TABLENAME;


}
