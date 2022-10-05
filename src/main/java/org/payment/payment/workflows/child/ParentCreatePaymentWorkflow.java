package org.payment.payment.workflows.child;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.payment.payment.dto.PaymentDto;

@WorkflowInterface
public interface ParentCreatePaymentWorkflow {

    @WorkflowMethod
    String createPayment(PaymentDto paymentDto);
}

