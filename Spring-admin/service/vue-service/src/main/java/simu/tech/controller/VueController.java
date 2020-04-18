package simu.tech.controller;

import simu.tech.Test.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/find")
public class VueController {

    @Autowired
    private Demo demo;

    @RequestMapping(value = "/Demo",method = {RequestMethod.GET})
    @ResponseBody
    public String Demo(){
        demo.demo();
        return "111";
    }
    @RequestMapping(value = "/hello",method = {RequestMethod.GET})
    public String hello(){
        return "/index";
    }

    @RequestMapping(value = "/simuHomePage",method = {RequestMethod.GET})
    public String simuHomePage(){
        return "/simuHomePage";
    }
    @RequestMapping(value = "/Company",method = {RequestMethod.GET})
    public String Company(){
        return "/Company";
    }
    @RequestMapping(value = "/business",method = {RequestMethod.GET})
    public String business(){
        return "/business";
    }
    @RequestMapping(value = "/orsUser",method = {RequestMethod.GET})
    public String orsUser(){
        return "/orsUser";
    }
}
