package simu.tech.service;

import simu.tech.pojo.TestBean;

import java.util.Map;

public interface SearchService {

    /**
     * 全文检索
     * @param
     * @return
     * @throws Exception
     */
    public Iterable<TestBean> serach();
}
