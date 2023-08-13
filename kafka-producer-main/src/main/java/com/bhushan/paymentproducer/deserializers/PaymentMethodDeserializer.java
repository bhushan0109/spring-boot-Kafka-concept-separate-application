package com.bhushan.paymentproducer.deserializers;

import java.io.IOException;

import com.bhushan.paymentproducer.enums.PaymentMethod;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PaymentMethodDeserializer extends JsonDeserializer<PaymentMethod> {

    @Override
    public PaymentMethod deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getText();
        if ("C".equalsIgnoreCase(value)) {
            return PaymentMethod.CREDIT;
        } else if ("D".equalsIgnoreCase(value)) {
            return PaymentMethod.DEBIT;
        } else {
        }
		return null;
    }
}
