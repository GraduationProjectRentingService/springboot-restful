package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.spring.springboot.dao.MessageDao;
import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.Message;
import org.spring.springboot.domain.User;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.UserService;
import org.spring.springboot.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.spring.springboot.domain.ResponseBean.FAIL_CODE;
import static org.spring.springboot.domain.ResponseBean.SUCCESS_CODE;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public ResponseBean login(User user) {
        ResponseBean responseBean = new ResponseBean();

        User userInDao = userDao.findByPhone(user.getPhoneNumber());
        if (userInDao == null){
            responseBean.setCode(ResponseBean.FAIL_CODE);
            responseBean.setMessage("该账户还未注册!");
            return responseBean;
        }

        if (user.getPassword().equals(userInDao.getPassword())){
            String token = SecurityUtils.MD5(user.getPhoneNumber()+user.getPassword()+System.currentTimeMillis());
            user.setToken(token);
            userDao.updateToken(user.getPhoneNumber(), user.getToken());
            responseBean.setCode(SUCCESS_CODE);
            JSONObject content = new JSONObject();
            content.put("token", token);
            responseBean.setContent(content);
        } else {
            responseBean.setCode(ResponseBean.FAIL_CODE);
            responseBean.setMessage("密码错误!");
        }
        return responseBean;
    }

    @Override
    public ResponseBean saveUser(User user) {
        ResponseBean responseBean = new ResponseBean();
        if (userDao.findByPhone(user.getPhoneNumber()) == null){
            String token = SecurityUtils.MD5(user.getPhoneNumber()+user.getPassword()+System.currentTimeMillis());
            user.setToken(token);
            userDao.saveUser(user);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("注册成功");
            JSONObject content = new JSONObject();
            content.put("token", token);
            responseBean.setContent(content);
        }else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("账户已存在，不需要重新注册");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean updateUser(User user) {
        ResponseBean responseBean = new ResponseBean();
        userDao.updateUser(user);
        responseBean.setMessage("修改用户信息成功");
        responseBean.setCode(SUCCESS_CODE);
        return responseBean;
    }

    @Override
    public ResponseBean getUserInfo(String phone) {
        ResponseBean responseBean = new ResponseBean();
        User user = userDao.findByPhone(phone);
        if (user != null){
            responseBean.setMessage("获取用户信息成功！");
            responseBean.setCode(SUCCESS_CODE);
            JSONObject content = new JSONObject();
            user.setPassword("");
            user.setToken("");
            content.put("user", user);
            responseBean.setContent(content);
        }else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("用户不存在");
        }
        return responseBean;
    }

    @Override
    public ResponseBean deleteUser(Long id) {
        return null;
    }

    @Override
    public boolean isUserTokenLegal(String phoneNum, String token) {
        User user = userDao.findByPhone(phoneNum);
        return token != null && user != null && token.equals(user.getToken());
    }

    @Override
    public ResponseBean getAllUserList(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if ( json.getString("managementId").equals("Admin") && json.getString("password").equals("123456") ) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(userDao.findAllUser());
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getAllInfo(User user){
        JSONObject content = new JSONObject();
        content.put("list", messageDao.getAll());
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "获取成功！", content);
    }

}
