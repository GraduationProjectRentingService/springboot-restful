package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.spring.springboot.dao.HouseDao;
import org.spring.springboot.dao.OrderDao;
import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.Order;
import org.spring.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.spring.springboot.domain.ResponseBean;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.spring.springboot.domain.ResponseBean.FAIL_CODE;
import static org.spring.springboot.domain.ResponseBean.SUCCESS_CODE;

/**
 * Created by Administrator on 2018/3/31.
 */

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private HouseDao houseDao;

    @Override
    public ResponseBean checkBookingReservations(String str) {
        return null;
    }

    @Override
    public ResponseBean createOneOrder(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        Long id = (orderDao.FindMaxOrderId()) + 1;
        Order order = new Order();
        order.setId(id);
        order.setHouseId(json.getLong("houseId"));
        order.setHouseTitle(houseDao.getHouseTitle(json.getLong("houseId")));
        order.setHostId(houseDao.getHostId(json.getLong("houseId")));
        order.setUserId(json.getString("phoneNumber"));
        order.setStartTime(json.getTimestamp("startTime"));
        order.setEndTime(json.getTimestamp("endTime"));
        order.setDateNum(json.getLong("dateNum"));
        orderDao.createAnOrder(order);

        responseBean.setCode(SUCCESS_CODE);
        responseBean.setMessage("生成订单成功");
        responseBean.setContent(id);
        return responseBean;
    }

    @Override
    public ResponseBean getOrder(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if( json.getLong("orderState") != null){
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("获取与房东有关的订单成功");
            responseBean.setContent(orderDao.getAllOrder(json.getLong("hostId")));
          }
        else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取订单失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getSettlementStatistics(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if( json.getString("hostId") != null){
            List<Order> orders = orderDao.getSettlement(json.getString("hostId"));
            //怎么累加???
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("获取收入情况成功");
            responseBean.setContent("");
        }
        else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取收入情况失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

}
