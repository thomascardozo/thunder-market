package com.tm.producer.configs;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.tm.commons.constants.RabbitMQContants.*;

@Configuration
public class RabbitMQConfig {


    @Bean
    public Queue queue(){
        return new Queue(QUEUE_PRODUCT_LOG, false, false,false);
    }

    @Bean
    public Queue queueTopic(){
        return new Queue(QUEUE_PRODUCT_ORDER, true, false,false);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXG_NAME_MKTPLACE,false,false);
    }

    @Bean
    public TopicExchange topicExchange() {return new TopicExchange(EXG_TOPIC_MKTPLACE,true,true);}

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(directExchange())
                .with(RK_PRODUCT_LOG);
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder
                .bind(queueTopic())
                .to(topicExchange())
                .with(RK_PRODUCT_ORDER);
    }
}
