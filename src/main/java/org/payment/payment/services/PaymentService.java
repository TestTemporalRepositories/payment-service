package org.payment.payment.services;

import org.payment.payment.dto.PaymentDto;

public interface PaymentService {
    String createPayment(PaymentDto paymentDto);
    String createPaymentChild(PaymentDto paymentDto);
}
