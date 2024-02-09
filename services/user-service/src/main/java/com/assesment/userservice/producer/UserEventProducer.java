package com.assesment.userservice.producer;

import com.assesment.userservice.dto.UserEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class UserEventProducer {
    @Value("${spring.kafka.topic}")
    public String topic;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public UserEventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    public CompletableFuture<SendResult<String, String>> sendUserEvent(UserEvent userEvent) throws JsonProcessingException {
        String key  = userEvent.getEventId().toString();
        String value = objectMapper.writeValueAsString(userEvent);
        CompletableFuture<SendResult<String,String>> completableFuture
                =  kafkaTemplate.send(topic,key,value);
        return completableFuture
                .whenComplete((SendResult, throwable) -> {
                    if(throwable!=null){
                        handleException(key,value,throwable);
                    }else {
                        handleSuccess(key,value,SendResult);
                    }
                });
    }
    public SendResult<String, String> sendUserEventSyncronus(UserEvent userEvent)
            throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        String key  = userEvent.getEventId().toString();
        String value = objectMapper.writeValueAsString(userEvent);
        SendResult<String,String> result
                =  kafkaTemplate.send(topic,key,value).get(3, TimeUnit.SECONDS);
        return result;
    }
    private void handleSuccess(String key, String value, SendResult<String,String> sendResult){
        log.error("Sending the message to the producer is Successfull. Exception is {}", sendResult.getRecordMetadata().partition());
    }

    private void handleException(String key, String value, Throwable throwable){
        log.error("Error Sending the message to the producer. Exception is {}", throwable.getMessage(),throwable);
    }
}

