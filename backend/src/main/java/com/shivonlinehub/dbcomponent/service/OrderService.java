package com.shivonlinehub.dbcomponent.service;

import com.shivonlinehub.dbcomponent.model.Order;
import com.shivonlinehub.dbcomponent.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public String placeOrder(Order order) {
        orderRepository.save(order);
        return "Order placed successfully!";
    }
}
