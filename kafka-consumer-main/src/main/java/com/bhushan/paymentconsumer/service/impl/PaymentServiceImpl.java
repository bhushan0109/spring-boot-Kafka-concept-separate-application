package com.bhushan.paymentconsumer.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bhushan.paymentconsumer.domain.dto.PaymentDTO;
import com.bhushan.paymentconsumer.domain.entity.PaymentEntity;
import com.bhushan.paymentconsumer.enums.PaymentStatus;
import com.bhushan.paymentconsumer.exception.PaymentProcessException;
import com.bhushan.paymentconsumer.repository.PaymentRepository;
import com.bhushan.paymentconsumer.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentRepository paymentRepository;

    private final ModelMapper mapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }

    @Override
    public void processPayment(PaymentDTO paymentDTO) {

        paymentRepository.findById(paymentDTO.getId())
                .ifPresent(payment -> {
                    logger.error("The UUID {} for that payment is already registered in the database", paymentDTO.getId());
                    /* Callback measure for this scenario, sending request to payment failure API */
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getForEntity("https://google.com/", String.class);
                    throw new PaymentProcessException("Error to process payment");
                });

        /* Business rule, call from other API's to validate payment... */

        paymentDTO.setStatus(PaymentStatus.PROCESSED);
        logger.info("Processed payment -> {}", paymentDTO);

        /* End validations and Business rules */

        PaymentEntity entity = mapper.map(paymentDTO, PaymentEntity.class);

        paymentRepository.save(entity);

    }
}
