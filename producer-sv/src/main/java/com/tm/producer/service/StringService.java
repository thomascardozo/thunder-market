package com.tm.producer.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.tm.commons.constants.RabbitMQContants.*;


@Service
@Log4j2
public class StringService {

    private final RabbitTemplate rabbitTemplate;

    public StringService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(String message){
        log.info("Received message " + message);
        rabbitTemplate.convertAndSend(EXG_NAME_MKTPLACE, RK_PRODUCT_LOG, message);
        rabbitTemplate.convertAndSend(EXG_TOPIC_MKTPLACE, RK_PRODUCT_LOG, message);
    }

}
