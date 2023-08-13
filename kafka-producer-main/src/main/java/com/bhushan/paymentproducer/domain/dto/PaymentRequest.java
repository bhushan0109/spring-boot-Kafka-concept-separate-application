package com.bhushan.paymentproducer.domain.dto;

//import java.io.Serial;
import java.io.Serializable;

import com.bhushan.paymentproducer.deserializers.PaymentMethodDeserializer;
import com.bhushan.paymentproducer.enums.PaymentMethod;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PaymentRequest implements Serializable {

   // @Serial
    private static final long serialVersionUID = -7436653799965582143L;

    private String customerName;

    @JsonDeserialize(using = PaymentMethodDeserializer.class)
    private PaymentMethod paymentMethod;

    private Double paymentAmount;

    public PaymentRequest() {

    }

    public PaymentRequest(String customerName, PaymentMethod paymentMethod, Double paymentAmount) {
        this.customerName = customerName;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
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

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "customerName='" + customerName + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", paymentAmount=" + paymentAmount +
                '}';
    }

}
