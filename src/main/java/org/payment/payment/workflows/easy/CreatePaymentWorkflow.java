package org.payment.payment.workflows.easy;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.payment.payment.dto.PaymentDto;

@WorkflowInterface
public interface CreatePaymentWorkflow {

    @WorkflowMethod
    String createPayment(PaymentDto paymentDto);
}

