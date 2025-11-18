package com.carenest.app.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ configuration for job broadcasting.
 * 
 * Defines a fanout exchange that broadcasts job-created events
 * to all bound helper queues.
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Name of the exchange used for broadcasting job creation events.
     */
    public static final String JOB_BROADCAST_EXCHANGE = "jobs.broadcast.exchange";

    /**
     * Name of the queue that helper service(s) will listen to.
     * In a real system, there could be multiple queues, one per helper app.
     */
    public static final String HELPER_JOBS_QUEUE = "helper.jobs.queue";

    /**
     * Declares a durable queue for helper job notifications.
     * @return the helper jobs queue
     */
    @Bean
    public Queue helperJobsQueue() {
        return new Queue(HELPER_JOBS_QUEUE, true);
    }

    /**
     * Declares a fanout exchange used to broadcast job-related events.
     * @return the fanout exchange
     */
    @Bean
    public FanoutExchange jobBroadcastExchange() {
        return new FanoutExchange(JOB_BROADCAST_EXCHANGE, true, false);
    }

    /**
     * Binds the helper jobs queue to the job broadcast exchange.
     * This ensures all job creation events are delivered to the queue.
     * @param helperJobsQueue the helper jobs queue bean
     * @param jobBroadcastExchange the job broadcast exchange bean
     * @return the binding between exchange and queue
     */
    @Bean
    public Binding helperJobsBinding(Queue helperJobsQueue, FanoutExchange jobBroadcastExchange) {
        return BindingBuilder.bind(helperJobsQueue).to(jobBroadcastExchange);
    }
    
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }


}
