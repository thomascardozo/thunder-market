package com.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.tm.proxy","com.tm"})
public class CambioApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CambioApiApplication.class, args);
    }
}