package org.spring.springboot.controller;

import org.spring.springboot.domain.Order;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "user/checkLaterBookingReservations", method = RequestMethod.POST)
    public ResponseBean checkLaterBookingReservations(@RequestBody String params){
        return orderService.checkBookingReservations(params);
    }
}
