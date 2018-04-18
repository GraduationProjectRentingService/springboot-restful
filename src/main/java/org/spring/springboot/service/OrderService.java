package org.spring.springboot.service;

import org.spring.springboot.domain.Order;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.dto.OrderDto;

public interface OrderService {

//    ResponseBean checkBookingReservations(String str);
//
//    ResponseBean createOneOrder(String str);
//
//    ResponseBean getOrder(String str);

    ResponseBean createOrder(OrderDto order);

    ResponseBean updateOrder(OrderDto orderDto);

    ResponseBean getAllOrdersByUserPhone(String userPhone);//通过用户账号获取所有订单列表

    ResponseBean getAllOrdersByHostPhone(String hostPhone);//通过房东获取所有订单列表

    ResponseBean getOrdersByHostPhoneAndStatus(String hostPhone, int status);//通过订单情况和房东获取列表、

    ResponseBean getTotalFinishOrderMoney(String phone);//获取某房东的所有已完成的订单总额

}
