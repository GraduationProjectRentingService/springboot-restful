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
     * 检查订单的合法性
     * @param dto
     */
    private void checkOrder(OrderDto dto){
        House house = houseManagerService.findHouseById(dto.getHouseId());
        MyExceptionAssert.isTrue(house != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源不存在！");
//        MyExceptionAssert.isNotBlank(dto.getHouseTitle(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源标题不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHouseImgUrl(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源缩略图不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHouseLocation(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源位置不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHouseRentalType(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源出租类型不能为空！");
        MyExceptionAssert.isNotBlank(dto.getUserName(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "用户名不能为空！");
        MyExceptionAssert.isNotBlank(dto.getUserPhone(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "用户手机号码不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHostPhone(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房东手机号码不能为空！");
        MyExceptionAssert.isTrue(dto.getTotalHouseMoney() > 0, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源总价格有误！");
        MyExceptionAssert.isTrue(dto.getTotalMoney() > 0, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "订单总价有误！");
        MyExceptionAssert.isTrue(dto.getDayNum() > 0, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住天数不能小于1！");
        MyExceptionAssert.isTrue(dto.getCheckInDate() != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住时间不能为空！");
        MyExceptionAssert.isTrue(dto.getCheckOutDate() != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "离开时间不能为空！");
        MyExceptionAssert.isNotBlank(dto.getCheckInPeopleIdList(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住人不能为空！");

    }

    /**
     * create by sun
     */
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public ResponseBean createOrder(@RequestBody OrderDto orderDto){
        checkUser(orderDto);
        checkOrder(orderDto);
        orderDto.setStatus(Order.STATUS_UNPAY);
        return orderService.createOrder(orderDto);
    }
}
