package com.xlong.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value={"/home.html","/"}, method=RequestMethod.GET)
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @RequestMapping("/welcome.html")
    public ModelAndView welcome(){
        return new ModelAndView("welcome");
    }

    @RequestMapping("/question-list.html")
    public ModelAndView questionList(){
        return new ModelAndView("question-list");
    }

    @RequestMapping("/producter-list.html")
    public ModelAndView producterList(){
        return new ModelAndView("producter-list");
    }

    @RequestMapping("/consumer-list.html")
    public ModelAndView consumerList(){
        return new ModelAndView("consumer-list");
    }

    @RequestMapping("/request-list.html")
    public ModelAndView requestList(){
        return new ModelAndView("request-list");
    }

    @RequestMapping("/response-list.html")
    public ModelAndView responseList(){
        return new ModelAndView("response-list");
    }

    @RequestMapping("/admin-list.html")
    public ModelAndView adminList(){
        return new ModelAndView("admin-list");
    }

    @RequestMapping("/admin-permission.html")
    public ModelAndView permissionList(){
        return new ModelAndView("admin-permission");
    }

    @RequestMapping("/charts-pie.html")
    public ModelAndView chartsPie(){
        return new ModelAndView("charts-pie");
    }

    @RequestMapping("/charts-nodes.html")
    public ModelAndView chartsNodes(){
        return new ModelAndView("charts-nodes");
    }

    @RequestMapping("/charts-services.html")
    public ModelAndView chartsServices(){
        return new ModelAndView("charts-services");
    }

    @RequestMapping("/charts-time.html")
    public ModelAndView chartsTime(){
        return new ModelAndView("charts-time");
    }
}
