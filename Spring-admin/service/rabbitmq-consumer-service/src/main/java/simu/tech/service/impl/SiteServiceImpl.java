package simu.tech.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simu.tech.config.RabbitMQConfig;
import simu.tech.dao.SiteDao;
import simu.tech.dao.TaskDao;
import simu.tech.pojo.Task;
import simu.tech.service.SiteService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SiteServiceImpl implements SiteService {

//    @Autowired
//    private PmFeign pmFeign;

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private TaskDao taskDao;

    @Override
    public void updateUser(Integer addId, Integer redId, Integer mon) {
        siteDao.addUser(addId, mon);
        siteDao.redUser(redId, mon);
        //在这里要操作别的数据库,调用Feign接口,使用Feign接口时,不知道其它节点是否成功
//        Result result = pmFeign.updateUser();
//        System.out.println(result);

        //将对别的数据库操作添加到任务表中
        Task task = new Task();
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        task.setMqExchange(RabbitMQConfig.EX_ADDUSER);
        task.setMqRoutingkey(RabbitMQConfig.CG_ADDUSER_KEY);
        Map map = new HashMap();
        map.put("addId",addId);
        map.put("redId",redId);
        map.put("mon", mon);
        task.setRequestBody(JSON.toJSONString(map));
        taskDao.insertSelective(task);

    }
}
