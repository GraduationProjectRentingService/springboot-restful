package org.spring.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.HouseDao;
import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.exception.ExceptionResolver;
import org.spring.springboot.service.HouseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseManagerServiceImpl implements HouseManagerService{
    final Logger log = LoggerFactory.getLogger(HouseManagerServiceImpl.class);

    @Autowired
    HouseDao houseDao;

    @Override
    public ResponseBean createHouse(String phoneNum) {
        long id = houseDao.createHouse(phoneNum);
        log.info("create house: " + id);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "创建房源成功！", id);
    }
}
