package org.spring.springboot.service.impl;

import org.spring.springboot.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.spring.springboot.domain.ResponseBean;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/31.
 */

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderDao orderDao;

//    @Override
//    public ResponseBean checkBookingReservations(String str){
//
//    }
}
