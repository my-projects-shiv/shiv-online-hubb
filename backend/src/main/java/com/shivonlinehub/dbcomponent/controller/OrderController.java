package com.shivonlinehub.dbcomponent.controller;

import com.shivonlinehub.dbcomponent.model.Order;
import com.shivonlinehub.dbcomponent.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public String placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}
