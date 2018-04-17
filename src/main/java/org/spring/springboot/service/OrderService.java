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

}
