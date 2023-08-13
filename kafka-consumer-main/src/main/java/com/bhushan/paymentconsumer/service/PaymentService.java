package com.bhushan.paymentconsumer.service;

import com.bhushan.paymentconsumer.domain.dto.PaymentDTO;

public interface PaymentService {

    void processPayment(PaymentDTO paymentDTO);

}
