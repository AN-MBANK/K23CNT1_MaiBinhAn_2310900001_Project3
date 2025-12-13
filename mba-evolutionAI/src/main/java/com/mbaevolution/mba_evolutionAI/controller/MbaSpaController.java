package com.mbaevolution.mba_evolutionAI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MbaSpaController {

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}