package com.zomato.zomato.controller;

import com.zomato.zomato.request.OrderSummaryRequest;
import com.zomato.zomato.response.OrderSummaryResponse;
import com.zomato.zomato.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @PostMapping("/order-summary")
    public OrderSummaryResponse getOrderSummary(@RequestBody OrderSummaryRequest orderSummaryRequest) {
        log.info("Request payload for getOrderSummary {}", orderSummaryRequest);
        return orderService.getOrderSummary(orderSummaryRequest);
    }

}
