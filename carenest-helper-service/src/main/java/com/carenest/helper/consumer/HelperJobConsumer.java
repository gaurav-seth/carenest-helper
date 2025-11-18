package com.carenest.helper.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.carenest.app.config.RabbitMQConfig;
import com.carenest.helper.dto.JobCreatedMessage;

/**
 * RabbitMQ consumer that receives job-created events from the queue.
 * This simulates helpers being notified when a new job is available.
 */
@Component
public class HelperJobConsumer {

    /**
     * Listens on the helper jobs queue and receives job notifications.
     * This method is automatically invoked whenever a new message arrives.
     *
     * @param msg the job details sent from the patient side
     */
    @RabbitListener(queues = RabbitMQConfig.HELPER_JOBS_QUEUE)
    public void receiveJob(JobCreatedMessage msg) {
        System.out.println("\n🟢 NEW JOB RECEIVED BY HELPER SERVICE");
        System.out.println("   Patient Phone : " + msg.getPatientPhoneNumber());
        System.out.println("   Location      : " + msg.getLocation());
    }
}
