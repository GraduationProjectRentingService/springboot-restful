package org.spring.springboot.controller.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.Order;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.dto.BaseDto;
import org.spring.springboot.dto.HostDto;
import org.spring.springboot.dto.IBaseDto;
import org.spring.springboot.dto.OrderDto;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.HostService;
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
    @Autowired
    private HostService hostService;

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
    private void checkUser(IBaseDto dto){
        MyExceptionAssert.isNotBlank(dto.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "账号不能为空！");
        MyExceptionAssert.isNotBlank(dto.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(userService.getUserInfo(dto.getPhoneNumber()) != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不存在！");
    }

    /**
     * 检查用户是否登录
     * @param dto
     */
    private void checkHost(IBaseDto dto){
        MyExceptionAssert.isNotBlank(dto.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "账号不能为空！");
        MyExceptionAssert.isNotBlank(dto.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(hostService.findHostUserByPhoneNum(dto.getPhoneNumber()) != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不存在！");
    }


    /**
     * create by sun
     */
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public ResponseBean createOrder(@RequestBody OrderDto orderDto){
        checkUser(orderDto);
        //判断token是否过期
        if (!userService.isUserTokenLegal(orderDto.getPhoneNumber(), orderDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        return orderService.createOrder(orderDto);
    }

    /**
     * 修改订单状态为支付状态
     * @param orderDto
     * @return
     */
    @RequestMapping(value = "payOrder", method = RequestMethod.POST)
    public ResponseBean payOrder(@RequestBody OrderDto orderDto){
        checkUser(orderDto);
        //判断token是否过期
        if (!userService.isUserTokenLegal(orderDto.getPhoneNumber(), orderDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        MyExceptionAssert.isNotBlank(orderDto.getPayWay(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION,"支付方式不能为空！");
        MyExceptionAssert.isTrue(orderDto.getPayWayCode() > 0 , MyExceptionCode.PARAM_REQUIRED_EXCEPTION,"支付类型有误！");
        orderDto.setStatus(Order.STATUS_FINISH);
        return orderService.updateOrder(orderDto);
    }

    /**
     * 修改订单状态为取消状态
     * @param orderDto
     * @return
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    public ResponseBean cancelOrder(@RequestBody OrderDto orderDto){
        checkUser(orderDto);
        //判断token是否过期
        if (!userService.isUserTokenLegal(orderDto.getPhoneNumber(), orderDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        orderDto.setStatus(Order.STATUS_CANCEL);
        return orderService.updateOrder(orderDto);
    }

    /**
     * 获取用户所有订单
     * @param baseDto
     * @return
     */
    @RequestMapping(value = "getAllOrderByUserPhone", method = RequestMethod.POST)
    public ResponseBean getAllOrdersByUserPhone(@RequestBody BaseDto baseDto){
        checkUser(baseDto);
        //判断token是否过期
        if (!userService.isUserTokenLegal(baseDto.getPhoneNumber(), baseDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        return orderService.getAllOrdersByUserPhone(baseDto.getPhoneNumber());
    }

    /**
     * 获取房东所有订单
     * @param baseDto
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "getAllOrderByHostPhone", method = RequestMethod.POST)
    public ResponseBean getAllOrdersByHostPhone(@RequestBody BaseDto baseDto){
        checkHost(baseDto);
        //判断token是否过期
        if (!hostService.isHostUserTokenLegal(baseDto.getPhoneNumber(), baseDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        return orderService.getAllOrdersByHostPhone(baseDto.getPhoneNumber());
    }

    @CrossOrigin
    @RequestMapping(value = "getCancelOrdersByHostPhone", method = RequestMethod.POST)
    public ResponseBean getCancelOrdersByHostPhone(@RequestBody BaseDto baseDto){
        checkHost(baseDto);
        //判断token是否过期
        if (!hostService.isHostUserTokenLegal(baseDto.getPhoneNumber(), baseDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        return orderService.getOrdersByHostPhoneAndStatus(baseDto.getPhoneNumber(), Order.STATUS_CANCEL);
    }

    @CrossOrigin
    @RequestMapping(value = "getFinishOrdersByHostPhone", method = RequestMethod.POST)
    public ResponseBean getFinishOrdersByHostPhone(@RequestBody BaseDto baseDto){
        checkHost(baseDto);
        //判断token是否过期
        if (!hostService.isHostUserTokenLegal(baseDto.getPhoneNumber(), baseDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        return orderService.getOrdersByHostPhoneAndStatus(baseDto.getPhoneNumber(), Order.STATUS_FINISH);
    }

    @CrossOrigin
    @RequestMapping(value = "getUnPayOrdersByHostPhone", method = RequestMethod.POST)
    public ResponseBean getNoPayOrdersByHostPhone(@RequestBody BaseDto baseDto){
        checkHost(baseDto);
        //判断token是否过期
        if (!hostService.isHostUserTokenLegal(baseDto.getPhoneNumber(), baseDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        return orderService.getOrdersByHostPhoneAndStatus(baseDto.getPhoneNumber(), Order.STATUS_UNPAY);
    }

    @CrossOrigin
    @RequestMapping(value = "getTotalFinishOrderMoney", method = RequestMethod.POST)
    public ResponseBean getTotalFinishOrderMoney(@RequestBody BaseDto baseDto){
        checkHost(baseDto);
        //判断token是否过期
        if (!hostService.isHostUserTokenLegal(baseDto.getPhoneNumber(), baseDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token 过期，请重新登录！", "");
        }
        return orderService.getTotalFinishOrderMoney(baseDto.getPhoneNumber());
    }
}
