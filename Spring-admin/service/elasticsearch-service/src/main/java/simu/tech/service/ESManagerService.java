package simu.tech.service;

import simu.tech.entity.Result;

import java.io.FileNotFoundException;
import java.util.Map;

public interface ESManagerService {
    //创建索引库和映射关系
    void createIndexAndMapping();

    //导入全部数据到ES索引库
    void importAll() throws Exception;

    //根据spuId导入数据到ES索引库
    void importDataToESBySpuId(String spuId);

    //根据souid删除es索引库中相关的sku数据
    void delDataBySpuId(String id);

    //根据单参数分页查询
    Result selectByParam(String param);

    Result selectByParams(String str1, String str2);
}
