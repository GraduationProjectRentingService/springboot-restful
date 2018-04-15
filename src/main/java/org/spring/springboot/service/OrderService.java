package org.spring.springboot.service;

import org.spring.springboot.domain.ResponseBean;

public interface OrderService {

    ResponseBean checkBookingReservations(String str);

    ResponseBean createOneOrder(String str);

    ResponseBean getOrder(String str);
}
