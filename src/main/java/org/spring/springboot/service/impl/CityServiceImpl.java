package org.spring.springboot.service.impl;

import org.spring.springboot.dao.CityDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务逻辑实现类
 *
 * Created by bysocket on 07/02/2018
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> findAllCity(){
        return cityDao.findAllCity();
    }

    public ResponseBean findCityById(Long id) {
        City city = cityDao.findById(id);
        ResponseBean responseBean = new ResponseBean();
        if (city != null){
            responseBean.setCode(0);
            responseBean.setMessage("success!");
            responseBean.setContent(city);
        }else {
            responseBean.setCode(1);
            responseBean.setMessage("fail!");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        return cityDao.updateCity(city);
    }

    @Override
    public Long deleteCity(Long id) {
        return cityDao.deleteCity(id);
    }

}
