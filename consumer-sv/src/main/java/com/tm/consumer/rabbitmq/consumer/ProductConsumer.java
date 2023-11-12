package com.tm.consumer.rabbitmq.consumer;


import com.tm.commons.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.tm.commons.constants.RabbitMQContants.RK_PRODUCT_LOG;
import static com.tm.commons.constants.RabbitMQContants.RK_PRODUCT_ORDER;

@Component
@Log4j2
public class ProductConsumer {

    @RabbitListener(queues = {RK_PRODUCT_LOG})
    public void consumer(ProductDTO dto){
        log.info("Received msg " + dto.toString() );

    }

    @RabbitListener(queues = {RK_PRODUCT_ORDER})
    public void consumerTopic(ProductDTO dto){
        log.info("Received msg topic" + dto.toString() );

    }
}
