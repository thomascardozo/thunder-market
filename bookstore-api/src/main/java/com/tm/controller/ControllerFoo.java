package com.tm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("foo")
public class ControllerFoo {

    @GetMapping
    public String getFoo(){
        return "Donkey Kung Foo!";
    }
}
