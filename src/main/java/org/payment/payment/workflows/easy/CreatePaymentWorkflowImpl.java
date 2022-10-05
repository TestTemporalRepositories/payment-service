package org.payment.payment.workflows.easy;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.ActivityStub;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.payment.payment.dto.PaymentDto;

import java.time.Duration;

@Slf4j
public class CreatePaymentWorkflowImpl implements CreatePaymentWorkflow {

    private static final String BANK_ACTIVITY_QUEUE = "BANK-TASK-QUEUE";
    private static final String NOTIFY_ACTIVITY_QUEUE = "NOTIFY-TASK-QUEUE";

    @Override
    public String createPayment(PaymentDto paymentDto) {
        ActivityStub bankActivity = getBankActivity();
        ActivityStub notifyActivity = getNotifyActivity();
        bankActivity.execute("PayOut", Void.class, paymentDto);
        bankActivity.execute("PayIn", Void.class, paymentDto);
        String result = notifyActivity.execute("Notify", String.class, "Оплата завершена!");
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

    private ActivityStub getNotifyActivity() {
        ActivityOptions activityOptions = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(300L))
                .setTaskQueue(NOTIFY_ACTIVITY_QUEUE)
                .build();
        return Workflow.newUntypedActivityStub(activityOptions);
    }
}
