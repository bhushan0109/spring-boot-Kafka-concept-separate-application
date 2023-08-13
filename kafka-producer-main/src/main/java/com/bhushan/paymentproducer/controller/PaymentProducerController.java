package com.bhushan.paymentproducer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhushan.paymentproducer.domain.dto.PaymentDTO;
import com.bhushan.paymentproducer.domain.dto.PaymentRequest;
import com.bhushan.paymentproducer.service.PaymentProducerService;
import com.bhushan.paymentproducer.service.impl.PaymentProducerServiceImpl;

@RestController
@RequestMapping("/payment")
public class PaymentProducerController {

    private final PaymentProducerService paymentProducerService;

    public PaymentProducerController(PaymentProducerServiceImpl paymentService) {
        this.paymentProducerService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Object> sendMessage(@RequestBody PaymentRequest paymentRequest) {
        PaymentDTO response = paymentProducerService.send(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
