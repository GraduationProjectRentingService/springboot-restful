package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.CheckInPeopleDao;
import org.spring.springboot.dao.HouseDao;
import org.spring.springboot.dao.OrderDao;
import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.CheckInPeople;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.Order;
import org.spring.springboot.dto.OrderDto;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.spring.springboot.domain.ResponseBean;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.spring.springboot.domain.ResponseBean.FAIL_CODE;
import static org.spring.springboot.domain.ResponseBean.SUCCESS_CODE;

/**
 * Created by Administrator on 2018/3/31.
 */

@Service
public class OrderServiceImpl implements OrderService{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private CheckInPeopleDao checkInPeopleDao;
    @Autowired
    private UserDao userDao;

    @Override
    public ResponseBean createOrder(OrderDto order) {
        checkOrder(order);
        order.setStatus(Order.STATUS_UNPAY);//设置为未支付状态
        String ids[] = order.getCheckInPeopleIdList().split(",");
        MyExceptionAssert.isTrue(ids.length > 0, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住人信息参数有误！");
        List<CheckInPeople> list = new ArrayList<>();
        for (String checkPeopleId: ids){
            CheckInPeople checkInPeople = checkInPeopleDao.findById(Integer.parseInt(checkPeopleId));
            MyExceptionAssert.isTrue(checkInPeople != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住人不存在！");
            list.add(checkInPeople);
        }
        order.setOrderId(orderDao.findMaxOrderId() + 1);
        order.setCheckInPeopleUserInfoList(list);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        logger.info(order.toString());
        orderDao.createOrder(order);//在数据库中创建一条数据
        order.setToken("");//清空token
        JSONObject content = new JSONObject();
        content.put("order", order);
        return new ResponseBean(SUCCESS_CODE, "创建订单成功！", content);
    }

    @Override
    public ResponseBean updateOrder(OrderDto orderDto) {
        Order find  = orderDao.findOrderById(orderDto.getOrderId());
        if (find == null){
            return new ResponseBean(FAIL_CODE, "更新订单状态失败！", "");
        }

        //需要判断要更新的类型
        switch (orderDto.getStatus()){
            case Order.STATUS_UNPAY:
                if (find.getStatus() == Order.STATUS_CANCEL || find.getStatus() == Order.STATUS_FINISH){
                    return new ResponseBean(FAIL_CODE, "非法状态修改！", "");
                }
                break;
            case Order.STATUS_CANCEL:
                if (find.getStatus() == Order.STATUS_FINISH){
                    return new ResponseBean(FAIL_CODE, "非法状态修改！", "");
                }
                break;
            case Order.STATUS_FINISH:
                //修改支付状态的话，需要修改支付类型,为了返回
                find.setPayWay(orderDto.getPayWay());
                find.setPayWayCode(orderDto.getPayWayCode());
                break;
        }
        //修改订单状态
        find.setStatus(orderDto.getStatus());
        orderDao.updateOrder(orderDto);
        JSONObject content = new JSONObject();
        content.put("order", find);
        return new ResponseBean(SUCCESS_CODE, "更新订单成功！", content);
    }

    private List<Order> addCheckInPeoplesByIds(List<Order> orders){
        List<Order> resultOrders = new ArrayList<>();
        for (Order order: orders){
            String ids[] = order.getCheckInPeopleIdList().split(",");
            List<CheckInPeople> checkInPeopleList = new ArrayList<>();
            for (String id: ids){
                checkInPeopleList.add(checkInPeopleDao.findById(Integer.parseInt(id)));
            }
            order.setCheckInPeopleUserInfoList(checkInPeopleList);
            resultOrders.add(order);
        }
        return resultOrders;
    }

    @Override
    public ResponseBean getAllOrdersByUserPhone(String userPhone) {
        List<Order> orders = orderDao.findAllOrderListByUserPhone(userPhone);
        JSONObject content = new JSONObject();
        content.put("list", addCheckInPeoplesByIds(orders));
        logger.info(content.toString());
        return new ResponseBean(SUCCESS_CODE, "获取成功！", content);
    }

    @Override
    public ResponseBean getAllOrdersByHostPhone(String hostPhone) {
        List<Order> orders = orderDao.findAllOrderListByHostPhone(hostPhone);
        if (orders == null || orders.size() == 0){
            return new ResponseBean(FAIL_CODE, "该用户的订单列表为空！", "");
        }
        JSONObject content = new JSONObject();
        content.put("list", addCheckInPeoplesByIds(orders));
        logger.info(content.toString());
        return new ResponseBean(SUCCESS_CODE, "获取成功！", content);
    }

    @Override
    public ResponseBean getOrdersByHostPhoneAndStatus(String hostPhone, int status) {
        List<Order> orders = orderDao.findOrderListByHostPhone(hostPhone, status);
        String tip = "";
        if (status == Order.STATUS_UNPAY){
            tip = "未支付订单列表";
        }else if (status == Order.STATUS_CANCEL){
            tip = "取消订单列表";
        }else if (status == Order.STATUS_FINISH){
            tip = "已完成订单列表";
        }
        if (orders == null || orders.size() == 0){
            return new ResponseBean(FAIL_CODE,  tip + "为空！", "");
        }
        JSONObject content = new JSONObject();
        content.put("list", addCheckInPeoplesByIds(orders));
        logger.info(content.toString());
        return new ResponseBean(SUCCESS_CODE, tip + "获取成功！", content);
    }

    @Override
    public ResponseBean getTotalFinishOrderMoney(String phone) {
        return new ResponseBean(SUCCESS_CODE, "获取成功！", orderDao.getTotalFinishOrderMoney(phone));
    }

    /**
     * 检查订单的合法性
     * @param dto
     */
    private void checkOrder(OrderDto dto){
        House house = houseDao.findByRoomId(dto.getHouseId());
        MyExceptionAssert.isTrue(house != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源不存在！");
        dto.setHostPhone(house.getHostId());
        dto.setUserPhone(dto.getPhoneNumber());//将用户登录的账号设置为下单用户的手机号码,设置下单用户的个人信息
        dto.setUserName(userDao.findByPhone(dto.getPhoneNumber()).getName());
        dto.setHouseTitle(house.getTitle());
        dto.setHouseImgUrl(house.getPicOne());
        dto.setHouseLocation(house.getAddress());
        dto.setHouseRentalType(house.getRentalTypeText());
        //判断入住日期
        MyExceptionAssert.isTrue(dto.getCheckInDate() != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住时间不能为空！");
        MyExceptionAssert.isTrue(dto.getCheckOutDate() != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "离开时间不能为空！");
        MyExceptionAssert.isTrue(dto.getCheckInDate().before(dto.getCheckOutDate()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住时间不能晚于或等于离开时间！");
        dto.setDayNum((int)(dto.getCheckOutDate().getTime() - dto.getCheckInDate().getTime())/(24*60*60*1000));//计算入住天数
        logger.info("dayNum:" + dto.getDayNum());
        MyExceptionAssert.isTrue(dto.getDayNum() >= house.getLeastDay() && dto.getDayNum() <=house.getMostDay(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住天数必须在于" + house.getLeastDay() + "~" + house.getMostDay() + "之间");
        //设置价格
        dto.setDeposit((int) house.getDeposit());
        dto.setTotalHouseMoney((int) (house.getDailyPrice() * dto.getDayNum()));
        dto.setTotalMoney(dto.getTotalHouseMoney() + dto.getDeposit());
        //判断入住人信息列表
        MyExceptionAssert.isNotBlank(dto.getCheckInPeopleIdList(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住人不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHouseTitle(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源标题不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHouseImgUrl(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源缩略图不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHouseLocation(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源位置不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHouseRentalType(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源出租类型不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getUserName(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "用户名不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getUserPhone(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "用户手机号码不能为空！");
//        MyExceptionAssert.isNotBlank(dto.getHostPhone(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房东手机号码不能为空！");
//        MyExceptionAssert.isTrue(dto.getTotalHouseMoney() > 0, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源总价格有误！");
//        MyExceptionAssert.isTrue(dto.getTotalOrderMoney() > 0, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "订单总价有误！");
//        MyExceptionAssert.isTrue(dto.getDayNum() > 0, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "入住天数不能小于1！");




    }

//
//    @Override
//    public ResponseBean checkBookingReservations(String str) {
//        return null;
//    }
//
//    @Override
//    public ResponseBean createOneOrder(String str){
//        ResponseBean responseBean = new ResponseBean();
//        JSONObject json = JSON.parseObject(str);
//        Long id = (orderDao.FindMaxOrderId()) + 1;
//        Order order = new Order();
//        order.setId(id);
//        order.setHouseId(json.getLong("houseId"));
//        order.setHouseTitle(houseDao.getHouseTitle(json.getLong("houseId")));
//        order.setHostId(houseDao.getHostId(json.getLong("houseId")));
//        order.setUserId(json.getString("phoneNumber"));
//        order.setStartTime(json.getTimestamp("startTime"));
//        order.setEndTime(json.getTimestamp("endTime"));
//        order.setDateNum(json.getLong("dateNum"));
//        orderDao.createAnOrder(order);
//
//        responseBean.setCode(SUCCESS_CODE);
//        responseBean.setMessage("生成订单成功");
//        responseBean.setContent(id);
//        return responseBean;
//    }
//
//    @Override
//    public ResponseBean getOrder(String str){
//        ResponseBean responseBean = new ResponseBean();
//        JSONObject json = JSON.parseObject(str);
//        if( json.getLong("orderState") != null){
//            responseBean.setCode(SUCCESS_CODE);
//            responseBean.setMessage("获取与房东有关的订单成功");
//            responseBean.setContent(orderDao.getAllOrder(json.getLong("hostId")));
//          }
//        else {
//            responseBean.setCode(FAIL_CODE);
//            responseBean.setMessage("获取订单失败");
//            responseBean.setContent("");
//        }
//        return responseBean;
//    }
}
