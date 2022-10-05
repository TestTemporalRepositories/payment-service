package org.payment.payment.workflows.versioning;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.payment.payment.dto.PaymentDto;

@WorkflowInterface
public interface VerCreatePaymentWorkflow {

    @WorkflowMethod
    String createPayment(PaymentDto paymentDto);
}

