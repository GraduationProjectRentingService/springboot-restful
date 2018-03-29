package org.spring.springboot.controller.host;

import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.HostService;
import org.spring.springboot.service.HouseManagerService;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "house")
public class HouseManagerController {

    @Autowired
    HostService hostService;
    @Autowired
    HouseManagerService houseManagerService;

    @RequestMapping(value = "publish/createHouse", method = RequestMethod.POST)
    public ResponseBean createHouse(@RequestBody Host host){
        MyExceptionAssert.isNotBlank(host.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房东账号不能为空！");
        MyExceptionAssert.isNotBlank(host.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(hostService.findHostUserByPhoneNum(host.getPhoneNumber()) != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不存在！");
        MyExceptionAssert.isTrue(hostService.isHostUserTokenLegal(host.getPhoneNumber(), host.getToken()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
        return houseManagerService.createHouse(host.getPhoneNumber());
    }
}
