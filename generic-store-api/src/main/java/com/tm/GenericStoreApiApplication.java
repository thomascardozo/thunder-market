package com.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;


@SpringBootApplication
@EnableFeignClients("com.tm.proxy")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class GenericStoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenericStoreApiApplication.class, args);
    }
}
