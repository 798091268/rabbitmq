package com.example.rabbitmq.test;

import com.example.rabbitmq.config.DelayRabbitConfig;
import com.example.rabbitmq.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by trumpkin 2019/12/4 10:53
 */

@Component
@Slf4j
public class DelaySender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDelay(Order order) {
        log.info("【订单生成时间】" + new Date().toString() +"【10S后检查订单是否已经支付】" + order.toString() );
        this.amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_DELAY_EXCHANGE, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY, order, message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1000 * 10 + "");
            return message;
        });
    }

}
