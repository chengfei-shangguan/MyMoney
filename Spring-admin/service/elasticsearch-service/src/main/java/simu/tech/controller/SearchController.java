package simu.tech.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import simu.tech.pojo.TestBean;
import simu.tech.service.SearchService;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    //对搜索形参带有特殊符号进行处理
    public void handlerSearchMap(Map<String, String> searchMap) {
        if (null != searchMap) {
            Set<Map.Entry<String, String>> entries = searchMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                if (entry.getKey().startsWith("spec_")) {
                    searchMap.put(entry.getKey(), entry.getValue().replace("+", "%2B"));//将'+'替换成'%2B'
                }
            }
        }
    }

    //全文检索
    @GetMapping("search")
    @ResponseBody
    public Iterable<TestBean> search() {

        Iterable<TestBean> testBeans = searchService.serach();
        return testBeans;
    }
}