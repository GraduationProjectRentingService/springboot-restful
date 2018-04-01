package org.spring.springboot.dao;

import org.spring.springboot.domain.Order;

import java.util.List;

public interface OrderDao {

    long FindMaxOrderId();

    int createAnOrder(Order order);

}
