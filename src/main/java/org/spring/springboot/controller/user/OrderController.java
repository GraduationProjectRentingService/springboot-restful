package org.spring.springboot.controller.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.Order;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.dto.OrderDto;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.HouseManagerService;
import org.spring.springboot.service.OrderService;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/4/1.
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HouseManagerService houseManagerService;

//    @CrossOrigin
//    @RequestMapping(value = "order/createOrder", method = RequestMethod.POST)
//    public ResponseBean createOrder(@RequestBody String params) {
//        return orderService.createOneOrder(params);
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "order/getAllOrder", method = RequestMethod.POST)
//    public ResponseBean getAllOrder(@RequestBody String params) {
//        return orderService.getOrder(params);
//    }

    /**
     * 检查用户是否登录
     * @param dto
     */
    private void checkUser(OrderDto dto){
        MyExceptionAssert.isNotBlank(dto.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不能为空！");
        MyExceptionAssert.isNotBlank(dto.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(userService.isUserTokenLegal(dto.getPhoneNumber(), dto.getToken()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
    }


    /**
     * create by sun
     */
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public ResponseBean createOrder(@RequestBody OrderDto orderDto){
        checkUser(orderDto);
        return orderService.createOrder(orderDto);
    }
}
