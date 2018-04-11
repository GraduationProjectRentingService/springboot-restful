package org.spring.springboot.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.spring.springboot.dao.HostDao;
import org.spring.springboot.dao.HouseDao;
import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.HostService;
import org.spring.springboot.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
            host.setToken(SecurityUtils.MD5(host.getPhoneNumber() + host.getPassword() + System.currentTimeMillis()));
            hostDao.updateToken(host.getPhoneNumber(), host.getToken());
            responseBean.setContent(host.getToken());
        } else {
            responseBean.setCode(ResponseBean.FAIL_CODE);
            responseBean.setMessage("密码错误!");
        }
        return responseBean;
    }

    /**
     * 注册时调动
     * @param host
     * @return
     */
    @Override
    public ResponseBean saveHost(Host host) {
        ResponseBean responseBean = new ResponseBean();
        if (hostDao.findByPhone(host.getPhoneNumber()) == null){
            host.setToken(SecurityUtils.MD5(host.getPhoneNumber() + host.getPassword() + System.currentTimeMillis()));
            hostDao.saveHost(host);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("注册成功");
            responseBean.setContent(host.getToken());
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
    public ResponseBean deleteHost(Long id) {
        return null;
    }

    @Override
    public ResponseBean createOneHouse(Host host) {
        return null;
    }

    @Override
    public Host findHostUserByPhoneNum(String phoneNum) {
        return hostDao.findByPhone(phoneNum);
    }

    @Override
    public boolean isHostUserTokenLegal(String phoneNum, String token) {
        Host host = hostDao.findByPhone(phoneNum);
        return token != null && host != null && token.equals(host.getToken());
    }

    @Override
    public ResponseBean getAllHost(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if ( json.getString("managementId").equals("Admin") && json.getString("password").equals("123456") ) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(hostDao.findAllUser());
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getHostByName(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if ( json.getString("hostName") != null ) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("根据房东名字查询房东信息成功");
            responseBean.setContent(hostDao.findHostUserByName(json.getString("hostName")));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取房东信息失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getHostByNickName(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if ( json.getString("nickName") != null ) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("根据房东昵称查询房东信息成功");
            responseBean.setContent(hostDao.findHostUserByNickName(json.getString("nickName")));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取房东信息失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getHostByphoneNum(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if ( json.getString("phoneNumber") != null ) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("根据房东电话号码查询房东信息成功");
            responseBean.setContent(hostDao.findHostUserByphoneNumber(json.getString("phoneNumber")));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取房东信息失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

}
