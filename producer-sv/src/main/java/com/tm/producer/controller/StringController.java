package com.tm.producer.controller;

import com.tm.producer.service.StringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produces")
@RequiredArgsConstructor
public class StringController {

    private final StringService stringService;

    @GetMapping
    public ResponseEntity<String> produces(@RequestParam("msg") String msg){
        stringService.produce(msg);

        return ResponseEntity.ok().body("Sending message!");
    }


}
