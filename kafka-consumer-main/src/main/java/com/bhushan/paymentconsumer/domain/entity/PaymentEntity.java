package com.bhushan.paymentconsumer.domain.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bhushan.paymentconsumer.enums.PaymentMethod;
import com.bhushan.paymentconsumer.enums.PaymentStatus;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private Double paymentAmount;

    @Column(nullable = false)
    private PaymentStatus status;

    public PaymentEntity() {
    }

    public PaymentEntity(UUID id, String customerName, PaymentMethod paymentMethod, Double paymentAmount, PaymentStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PaymentEntity that = (PaymentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
