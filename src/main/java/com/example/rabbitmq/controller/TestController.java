package com.example.rabbitmq.controller;

import com.example.rabbitmq.model.Order;
import com.example.rabbitmq.test.DelaySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trumpkin 2019/12/4 10:53
 */

@RestController
public class TestController {

    @Autowired
    private DelaySender delaySender;

    @GetMapping("/sendDelay")
    public Object sendDelay() {
        Order order1 = new Order();
        order1.setOrderStatus(0);
        order1.setOrderId("123456");
        order1.setOrderName("小米6");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("456789");
        order2.setOrderName("小米8");

        delaySender.sendDelay(order1);
        delaySender.sendDelay(order2);
        return "ok";
    }


}
