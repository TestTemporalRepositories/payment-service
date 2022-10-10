package org.payment.payment.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Выполнение Workflow")
public class WorkflowRest {

    private final PaymentService paymentService;


    @Operation(
            summary = "Новый перевод",
            description = "Выполнение простого workflow CreatePaymentWorkflow с несколькими Activity"
    )
    @PostMapping("payments")
    public ResponseEntity<String> test(
            @RequestBody PaymentDto paymentDto
    ) {
        return new ResponseEntity<>(
                paymentService.createPayment(paymentDto),
                HttpStatus.OK
        );
    }

    @Operation(
            summary = "Новый перевод с вложенным Workflow",
            description = "Выполнение workflow ParentCreatePaymentWorkflow с несколькими Activity и вложенным (child) Workflow"
    )
    @PostMapping("payments-with-child-workflow")
    public ResponseEntity<String> testChild(
            @RequestBody PaymentDto paymentDto
    ) {
        return new ResponseEntity<>(
                paymentService.createPaymentChild(paymentDto),
                HttpStatus.OK
        );
    }

    @Operation(
            summary = "Новый перевод с версионированием",
            description = "Выполнение workflow VerCreatePaymentWorkflow с использованием версий"
    )
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
