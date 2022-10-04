package org.payment.payment.config;

import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.payment.payment.workflows.GreetingWorkflowImpl;
import org.payment.payment.config.properties.WorkerProperties;
import org.payment.payment.config.properties.Workers;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;

@RequiredArgsConstructor
public class WorkerFactoryStarter implements ApplicationListener<ContextRefreshedEvent> {

    private final Map<String, WorkerProperties> workerPropertiesMap;
    private final WorkerFactory workerFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setPaymentServiceWorker(workerPropertiesMap.get(Workers.PAYMENT.name()));
        workerFactory.start();
    }

    private void setPaymentServiceWorker(WorkerProperties workerProperties) {
        Worker worker = workerFactory.newWorker(workerProperties.getQueueName());
    }
}
