package com.bhushan.paymentconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhushan.paymentconsumer.domain.entity.PaymentEntity;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
