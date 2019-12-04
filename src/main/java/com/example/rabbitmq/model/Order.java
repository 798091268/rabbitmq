package com.example.rabbitmq.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by trumpkin 2019/12/4 10:51
 */
@Data
public class Order implements Serializable {

    private String orderId; // 订单id

    private Integer orderStatus; // 订单状态 0：未支付，1：已支付，2：订单已取消

    private String orderName; // 订单名字

}
