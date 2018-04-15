package org.spring.springboot.controller.user;

import org.spring.springboot.domain.Order;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/4/1.
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin
    @RequestMapping(value = "order/createOrder", method = RequestMethod.POST)
    public ResponseBean createOrder(@RequestBody String params) {
        return orderService.createOneOrder(params);
    }

    @CrossOrigin
    @RequestMapping(value = "order/getAllOrder", method = RequestMethod.POST)
    public ResponseBean getAllOrder(@RequestBody String params) {
        return orderService.getOrder(params);
    }
}
