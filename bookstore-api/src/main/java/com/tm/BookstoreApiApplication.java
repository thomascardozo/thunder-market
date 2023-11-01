package com.tm;

import com.tm.proxy.CambioProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@EnableFeignClients("com.tm.proxy")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class BookstoreApiApplication {

        public static void main(String[] args) {
        SpringApplication.run(BookstoreApiApplication.class, args);
    }
}
