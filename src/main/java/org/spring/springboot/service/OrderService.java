package org.spring.springboot.service;

import org.spring.springboot.domain.ResponseBean;

public interface OrderService {

    ResponseBean checkBookingReservations(String str);
}
