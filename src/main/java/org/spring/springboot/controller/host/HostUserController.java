package org.spring.springboot.controller.host;

import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.HostService;
import org.spring.springboot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HostUserController {

    @Autowired
    private HostService hostService;

    //房东注册
    @CrossOrigin
    @RequestMapping(value = "/house/user/register", method = RequestMethod.POST)
    public ResponseBean registerOneUser(@RequestBody Host host) {
        MyExceptionAssert.isNotBlank(host.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不能为空！");
        MyExceptionAssert.isNotBlank(host.getPassword(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "密码不能为空！");
        MyExceptionAssert.isTrue(StringUtils.isPhoneNum(host.getPhoneNumber()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不合理！");
        return hostService.saveHost(host);
    }

    //房东登录
    @CrossOrigin
    @RequestMapping(value = "/house/user/login", method = RequestMethod.POST)
    public ResponseBean hostLogin(@RequestBody Host host) {
        MyExceptionAssert.isNotBlank(host.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不能为空！");
        MyExceptionAssert.isNotBlank(host.getPassword(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "密码不能为空！");
        return hostService.login(host);
    }

    //管理员获取所有房东信息
    @CrossOrigin
    @RequestMapping(value = "/management/userManagement/getAllHostUser", method = RequestMethod.POST)
    public ResponseBean getAllHostUser(@RequestBody String params) {
        return hostService.getAllHost(params);
    }

    //管理员根据房东名字筛选房东信息
    @CrossOrigin
    @RequestMapping(value = "/management/userManagement/getHostUserByName", method = RequestMethod.POST)
    public ResponseBean getHostUserByName(@RequestBody String params) {
        return hostService.getHostByName(params);
    }

    //管理员根据房东昵称筛选房东信息
    @CrossOrigin
    @RequestMapping(value = "/management/userManagement/getHostUserByNickName", method = RequestMethod.POST)
    public ResponseBean getHostUserByNickName(@RequestBody String params) {
        return hostService.getHostByNickName(params);
    }

    //管理员根据房东电话号码筛选房东信息
    @CrossOrigin
    @RequestMapping(value = "/management/userManagement/getHostUserByphoneNum", method = RequestMethod.POST)
    public ResponseBean getHostUserByphoneNum(@RequestBody String params) {
        return hostService.getHostByphoneNum(params);
    }

}
