package org.spring.springboot.controller.user;

import org.spring.springboot.domain.User;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.UserService;
import org.spring.springboot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseBean registerOneUser(@RequestBody User user) {
        //合法性检测
        MyExceptionAssert.isNotBlank(user.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不能为空！");
        MyExceptionAssert.isNotBlank(user.getPassword(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "密码不能为空！");
        MyExceptionAssert.isTrue(StringUtils.isPhoneNum(user.getPhoneNumber()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不合法！");
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseBean userLogin(@RequestBody User user) {
        //合法性检测
        MyExceptionAssert.isNotBlank(user.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不能为空！");
        MyExceptionAssert.isNotBlank(user.getPassword(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "密码不能为空！");
        return userService.login(user);
    }

    @RequestMapping(value = "/user/updateUserInfo", method = RequestMethod.POST)
    public ResponseBean updateUserInfo(@RequestBody User user){
        MyExceptionAssert.isNotBlank(user.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不能为空！");
        MyExceptionAssert.isNotBlank(user.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(userService.isUserTokenLegal(user.getPhoneNumber(), user.getToken()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user/getUserInfo", method = RequestMethod.POST)
    public ResponseBean getUserInfo(@RequestBody User user){
        MyExceptionAssert.isNotBlank(user.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不能为空！");
        MyExceptionAssert.isNotBlank(user.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(userService.isUserTokenLegal(user.getPhoneNumber(), user.getToken()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
        return userService.getUserInfo(user.getPhoneNumber());
    }

    //管理员获取所有房客信息
    @CrossOrigin
    @RequestMapping(value = "/management/userManagement/getAllUser", method = RequestMethod.POST)
    public ResponseBean getAllUser(@RequestBody String params) {
        return userService.getAllUserList(params);
    }


    @RequestMapping(value = "/message/getAll", method = RequestMethod.POST)
    public ResponseBean getAll(@RequestBody User user){
        MyExceptionAssert.isNotBlank(user.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不能为空！");
        MyExceptionAssert.isNotBlank(user.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(userService.isUserTokenLegal(user.getPhoneNumber(), user.getToken()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
        return userService.getAllInfo(user);
    }

}
