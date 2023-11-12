package com.tm.producer.service;

import com.tm.commons.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.tm.commons.constants.RabbitMQContants.*;


@Service
@Log4j2
@RequiredArgsConstructor
public class ProductService {

    private final RabbitTemplate rabbitTemplate;

    public void createProduct(ProductDTO dto) {
        log.info("Sending a msg to exchange " + dto.toString());

        rabbitTemplate.convertAndSend(EXG_NAME_MKTPLACE, RK_PRODUCT_LOG, dto.toString());
        rabbitTemplate.convertAndSend(EXG_TOPIC_MKTPLACE, RK_PRODUCT_ORDER, dto.toString());
    }

}
