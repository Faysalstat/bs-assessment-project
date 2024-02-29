package com.assesment.userservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserEventConsumer {

    @KafkaListener(topics = {"user-event"})
    public void onMessage(ConsumerRecord<String,String> consumerRecord){
        log.info("ConsumerRecords: {}",consumerRecord.value());
    }
}