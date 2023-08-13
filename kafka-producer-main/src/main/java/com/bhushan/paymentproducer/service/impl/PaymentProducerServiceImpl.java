package com.bhushan.paymentproducer.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bhushan.paymentproducer.domain.dto.PaymentDTO;
import com.bhushan.paymentproducer.domain.dto.PaymentRequest;
import com.bhushan.paymentproducer.enums.PaymentStatus;
import com.bhushan.paymentproducer.service.PaymentProducerService;

@Service
public class PaymentProducerServiceImpl implements PaymentProducerService {

    private final Logger logger = LoggerFactory.getLogger(PaymentProducerServiceImpl.class);

    private final KafkaTemplate<String, PaymentDTO> kafkaTemplate;
    private final String topic;

    public PaymentProducerServiceImpl(KafkaTemplate<String, PaymentDTO> kafkaTemplate,
                                      @Value("${topic.payment-topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public PaymentDTO send(PaymentRequest paymentRequest) {

        try {

            PaymentDTO paymentDTO = new PaymentDTO.Builder()
                    .id(UUID.randomUUID())
                    .customerName(paymentRequest.getCustomerName())
                    .paymentMethod(paymentRequest.getPaymentMethod())
                    .paymentAmount(paymentRequest.getPaymentAmount())
                    .status(PaymentStatus.ANALYSIS)
                    .build();

            kafkaTemplate.send(topic, paymentDTO);

            return paymentDTO;

        } catch (Exception e) {
            logger.error("Error to process payment -> {}", paymentRequest);
            
        }
		return null;

    }

}
