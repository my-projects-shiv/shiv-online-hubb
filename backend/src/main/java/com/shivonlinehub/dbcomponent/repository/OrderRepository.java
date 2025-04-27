package com.shivonlinehub.dbcomponent.repository;

import com.shivonlinehub.dbcomponent.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
