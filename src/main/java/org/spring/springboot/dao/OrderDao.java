package org.spring.springboot.dao;

import org.spring.springboot.domain.Order;

import java.util.List;

public interface OrderDao {



//    int createAnOrder(Order order);
//
//    List<Order> getAllOrder(Long houseId);
//
//    List<Order>  getAnOrder(Long orderState, Long hostId);

    /**
     * create by sun
     */
    int createOrder(Order order);//创建订单

    int findMaxOrderId();

    List<Order> getSettlement(String id);
}
