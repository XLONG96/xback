package com.xlong.back.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {
    @RequestMapping(value="/login.html",method=GET)
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping(value="/login-error",method=GET)
    public ModelAndView loginFail(Model model){
        model.addAttribute("msg","username/password error");
        return new ModelAndView("login");
    }

    @RequestMapping("/index.html")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
