package simu.tech.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simu.tech.dao.TaskDao;
import simu.tech.dao.TaskHisDao;
import simu.tech.pojo.Task;
import simu.tech.pojo.TaskHis;
import simu.tech.service.TaskServcie;

import java.util.Date;

@Service
@Transactional
public class TaskServcieImpl implements TaskServcie {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskHisDao taskHisDao;

    @Override
    public void delTask(Task task) {
        //1. 设置删除时间
        task.setDeleteTime(new Date());
        Long id = task.getId();
        task.setId(null);

        //bean复制
        TaskHis taskHis = new TaskHis();
        BeanUtils.copyProperties(task, taskHis);

        //记录任务信息
        taskHisDao.insertSelective(taskHis);

        //删除原任务
        task.setId(id);
        taskDao.deleteByPrimaryKey(task);
    }
}
