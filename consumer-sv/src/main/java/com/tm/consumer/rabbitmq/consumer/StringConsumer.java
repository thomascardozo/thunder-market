package com.tm.consumer.rabbitmq.consumer;


import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.tm.commons.constants.RabbitMQContants.RK_PRODUCT_LOG;

@Component
@Log4j2
public class StringConsumer {

    @RabbitListener(queues = {RK_PRODUCT_LOG})
    public void consumer(String msg){
        log.info("Consumer received a message: " + msg);

    }
}
