package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
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

    List<Order> findAllOrderListByUserPhone(@Param("userPhone") String userPhone);//通过用户手机号码查询所有订单列表
    List<Order> findOrderListByUserPhone(@Param("userPhone") String userPhone, @Param("status") int status);//通过用户手机号码和订单类型查询订单列表
    List<Order> findAllOrderListByHostPhone(@Param("hostPhone") String hostPhone);//通过房东查询所有订单列表
    List<Order> findOrderListByHostPhone(@Param("hostPhone") String hostPhone, @Param("status") int status);//通过房东号码和订单类型查询订单列表

    int findMaxOrderId();//查询id最大的值

}
