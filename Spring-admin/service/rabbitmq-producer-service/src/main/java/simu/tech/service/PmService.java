package simu.tech.service;


import simu.tech.pojo.Task;

public interface PmService {
    void updateUser(Integer addId, Integer redId, Integer mon);

    int updateUserMon(Task task);
}
