package simu.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import simu.tech.entity.Result;
import simu.tech.entity.StatusCode;
import simu.tech.service.ESManagerService;

import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ESManagerController {
    @Autowired
    private ESManagerService esManagerService;

    //创建索引库结构
    @GetMapping("/create")
    public Result create(){
        esManagerService.createIndexAndMapping();
        return new Result(true, StatusCode.OK,"创建索引库结构成功");
    }

    //导入全部数据
    @GetMapping("/importAll")
    public Result importAll()throws Exception{
        esManagerService.importAll();
        return new Result(true, StatusCode.OK,"导入全部数据成功");
    }

    //根据id进行删除
    @GetMapping("deleteById/{id}")
    public Result deleteById(@PathVariable("id")String id){
        esManagerService.delDataBySpuId(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    //根据单参数分页查询
    @GetMapping("selectByParam/{param}")
    public Result selectByParam(@PathVariable("param") String param){
        return esManagerService.selectByParam(param);
    }

    //根据复合查询
    @GetMapping("selectByParams/{str1}/{str2}")
    public Result selectByParams(@PathVariable("str1") String str1,
                                @PathVariable("str2") String str2){
        return esManagerService.selectByParams(str1,str2);
    }
}