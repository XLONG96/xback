package com.xlong.back.controller;

import com.xlong.back.repository.PermissionRepository;
import com.xlong.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @RequestMapping(value="/login.html",method=GET)
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping(value="/login-error",method=GET)
    public ModelAndView loginFail(Model model){
        model.addAttribute("msg","用户名或密码错误");
        return new ModelAndView("login");
    }

    @RequestMapping("/index.html")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping(value="/api/users", method= RequestMethod.POST)
    public Page getUsers(@RequestParam(value = "start", defaultValue = "0") Integer start,
                             @RequestParam(value = "length", defaultValue = "10") Integer size,
                             @RequestParam("draw") int draw){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Integer page = start/size;
        Pageable pageable = new PageRequest(page, size, sort);
        return userRepository.findAll(pageable);
    }

    @RequestMapping(value="/api/permissions", method= RequestMethod.POST)
    public Page getPermissions(@RequestParam(value = "start", defaultValue = "0") Integer start,
                             @RequestParam(value = "length", defaultValue = "10") Integer size,
                             @RequestParam("draw") int draw){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Integer page = start/size;
        Pageable pageable = new PageRequest(page, size, sort);
        return permissionRepository.findAll(pageable);
    }
}
