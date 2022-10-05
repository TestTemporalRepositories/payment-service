package org.payment.payment.services;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.payment.payment.config.properties.TemporalProperties;
import org.payment.payment.config.properties.WorkerProperties;
import org.payment.payment.config.properties.Workers;
import org.payment.payment.dto.PaymentDto;
import org.payment.payment.workflows.child.ParentCreatePaymentWorkflow;
import org.payment.payment.workflows.easy.CreatePaymentWorkflow;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final WorkflowClient workflowClient;
    private final WorkerProperties workerProperties;

    public PaymentServiceImpl(WorkflowClient workflowClient, TemporalProperties temporalProperties) {
        this.workflowClient = workflowClient;
        this.workerProperties = temporalProperties.getWorkers().get(Workers.PAYMENT.name());
    }

    @Override
    public String createPayment(PaymentDto paymentDto) {

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(workerProperties.getQueueName())
                .setWorkflowId("Pay_" + UUID.randomUUID())
                .build();

        CreatePaymentWorkflow workflow = workflowClient.newWorkflowStub(CreatePaymentWorkflow.class, options);
        return workflow.createPayment(paymentDto);
    }

    @Override
    public String createPaymentChild(PaymentDto paymentDto) {

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(workerProperties.getQueueName())
                .setWorkflowId("Pay_" + UUID.randomUUID())
                .build();

        ParentCreatePaymentWorkflow workflow = workflowClient.newWorkflowStub(ParentCreatePaymentWorkflow.class, options);
        return workflow.createPayment(paymentDto);
    }
}
