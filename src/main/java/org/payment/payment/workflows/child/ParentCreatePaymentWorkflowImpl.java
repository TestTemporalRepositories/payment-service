package org.payment.payment.workflows.child;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.ActivityStub;
import io.temporal.workflow.ChildWorkflowOptions;
import io.temporal.workflow.ChildWorkflowStub;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.payment.payment.dto.PaymentDto;

import java.time.Duration;
import java.util.UUID;

@Slf4j
public class ParentCreatePaymentWorkflowImpl implements ParentCreatePaymentWorkflow {

    private static final String BANK_ACTIVITY_QUEUE = "BANK-TASK-QUEUE";
    private static final String NOTIFY_ACTIVITY_QUEUE = "NOTIFY-TASK-QUEUE";

    @Override
    public String createPayment(PaymentDto paymentDto) {
        ActivityStub bankActivity = getBankActivity();
        bankActivity.execute("PayOut", String.class, paymentDto);
        bankActivity.execute("PayIn", String.class, paymentDto);

        String result = getChildNotifyWorkflow().execute(String.class, "Оплата прошла!");
        log.info(result);
        return "Operation completed";
    }

    private ActivityStub getBankActivity() {
        ActivityOptions activityOptions = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(300L))
                .setTaskQueue(BANK_ACTIVITY_QUEUE)
                .build();
        return Workflow.newUntypedActivityStub(activityOptions);
    }

    private ChildWorkflowStub getChildNotifyWorkflow() {
        ChildWorkflowOptions workflowOptions = ChildWorkflowOptions.newBuilder()
                .setWorkflowId(UUID.randomUUID().toString())
                .setTaskQueue(NOTIFY_ACTIVITY_QUEUE)
                .build();
        return Workflow.newUntypedChildWorkflowStub("NotifyWorkflow", workflowOptions);
    }
}
