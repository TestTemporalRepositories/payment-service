package org.payment.payment.rest;

import lombok.AllArgsConstructor;
import org.payment.payment.dto.PaymentDto;
import org.payment.payment.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WorkflowRest {

    private final PaymentService paymentService;

    @PostMapping("payments")
    public ResponseEntity<String> test(
            @RequestBody PaymentDto paymentDto
    ) {
        return new ResponseEntity<>(
                paymentService.createPayment(paymentDto),
                HttpStatus.OK
        );
    }

    @PostMapping("payments-with-child-workflow")
    public ResponseEntity<String> testChild(
            @RequestBody PaymentDto paymentDto
    ) {
        return new ResponseEntity<>(
                paymentService.createPaymentChild(paymentDto),
                HttpStatus.OK
        );
    }

    @PostMapping("payments-with-versioning-workflow")
    public ResponseEntity<String> testVersion(
            @RequestBody PaymentDto paymentDto
    ) {
        return new ResponseEntity<>(
                paymentService.createPaymentVersion(paymentDto),
                HttpStatus.OK
        );
    }
}
