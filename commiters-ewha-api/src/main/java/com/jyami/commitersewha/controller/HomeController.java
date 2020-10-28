package com.jyami.commitersewha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jyami on 2020/10/25
 */
@Controller
public class HomeController {

    @GetMapping(value = "/error")
    public ModelAndView errorPage(@RequestParam(value = "message") String errorMessage) {
        ModelAndView m = new ModelAndView("hello");
        m.addObject("message", errorMessage);
        return m;
    }

}
