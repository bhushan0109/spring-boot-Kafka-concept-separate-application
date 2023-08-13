package com.bhushan.paymentproducer.service;

import com.bhushan.paymentproducer.domain.dto.PaymentDTO;
import com.bhushan.paymentproducer.domain.dto.PaymentRequest;

public interface PaymentProducerService {

    PaymentDTO send(PaymentRequest paymentRequest);

}
