package com.bhushan.paymentconsumer.consumer;

import com.bhushan.paymentconsumer.domain.dto.PaymentDTO;
import com.bhushan.paymentconsumer.exception.PaymentProcessException;
import com.bhushan.paymentconsumer.service.PaymentService;
import com.bhushan.paymentconsumer.service.impl.PaymentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private final Logger logger = LoggerFactory.getLogger(PaymentConsumer.class);

    private final PaymentService paymentService;

    private final ObjectMapper mapper;

    public PaymentConsumer(PaymentServiceImpl paymentService, ObjectMapper mapper) {
        this.paymentService = paymentService;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "${topic.payment-topic}")
    public void consume(String message) {
        try {

            logger.info("Consuming message -> {}", message);

            PaymentDTO parsedMessage = mapper.readValue(message, PaymentDTO.class);

            paymentService.processPayment(parsedMessage);

        } catch (JsonProcessingException e) {
            logger.error("Error to convert message -> {}", message);
            throw new PaymentProcessException("Error processing sent data");
        }

    }

}
