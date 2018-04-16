package org.spring.springboot.dao;

import org.spring.springboot.domain.Order;

import java.util.List;

public interface OrderDao {

    long FindMaxOrderId();

    int createAnOrder(Order order);

    List<Order> getAllOrder(Long houseId);

    List<Order>  getAnOrder(Long orderState, Long hostId);

    List<Order> getSettlement(String id);
}
