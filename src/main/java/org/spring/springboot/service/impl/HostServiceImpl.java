package org.spring.springboot.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.spring.springboot.dao.HostDao;
import org.spring.springboot.dao.HouseDao;
import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.spring.springboot.domain.ResponseBean.FAIL_CODE;
import static org.spring.springboot.domain.ResponseBean.SUCCESS_CODE;

/**
 * 房东业务逻辑实现类
 *
 * Created by bysocket on 07/02/2018
 */
@Service
public class HostServiceImpl implements HostService {

    @Autowired
    private HostDao hostDao;

    @Autowired
    private HouseDao houseDao;

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public ResponseBean login(Host host) {
        ResponseBean responseBean = new ResponseBean();

        if (host==null){
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("参数出错!");
            responseBean.setContent("");
            return responseBean;
        }

        Host hostInDao = hostDao.findByPhone(host.getPhoneNumber());
        if (hostInDao == null){
            responseBean.setCode(ResponseBean.FAIL_CODE);
            responseBean.setMessage("该账户还未注册!");
            return responseBean;
        }

        if (host.getPassword().equals(hostInDao.getPassword())){
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("login success");
            responseBean.setContent("token");
        } else {
            responseBean.setCode(ResponseBean.FAIL_CODE);
            responseBean.setMessage("密码错误!");
        }
        return responseBean;
    }

    @Override
    public ResponseBean saveHost(Host host) {
        ResponseBean responseBean = new ResponseBean();
        if (hostDao.findByPhone(host.getPhoneNumber()) == null){
            hostDao.saveHost(host);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("注册成功");
            responseBean.setContent("");
        }else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("账户已存在，不需要重新注册");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean updateHost(Host host) {
        return null;
    }

    @Override
    public ResponseBean saveDescription(String str) {
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
            //House house = houseDao.findByRoomId(json.getLong("roomId"));
        if( houseDao.findByRoomId(json.getLong("roomId")) != null ) {
            House house = houseDao.findByRoomId(json.getLong("roomId"));
            house.setHostId(json.getString("userPhone"));
            house.setTitle(json.getString("title"));
            house.setDescription(json.getString("description"));
            house.setHouseInfo(json.getString("houseInfo"));
            house.setTrafficCondition(json.getString("trafficCondition"));
            house.setSurroundCondition(json.getString("surroundCondition"));
            houseDao.updateDescription(house);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("添加成功");
            responseBean.setContent("");
        }
        else{
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("添加失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean createOneHouse(Host host){
        ResponseBean responseBean = new ResponseBean();
        if(host.getToken()!=null)
        {
            House house = new House();
            house.setHostId(host.getPhoneNumber());
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("添加成功");
            responseBean.setContent(host.getPhoneNumber());
        }
        else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("添加失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean deleteHost(Long id) {
        return null;
    }

//    JSONArray arrays = json.getJSONArray("");
//    System.out.println(arrays.getString(0));
}
