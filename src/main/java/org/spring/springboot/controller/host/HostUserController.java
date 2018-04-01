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

    @CrossOrigin
    @RequestMapping(value = "/house/user/register", method = RequestMethod.POST)
    public ResponseBean registerOneUser(@RequestBody Host host) {
        MyExceptionAssert.isNotBlank(host.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不能为空！");
        MyExceptionAssert.isNotBlank(host.getPassword(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "密码不能为空！");
        MyExceptionAssert.isTrue(StringUtils.isPhoneNum(host.getPhoneNumber()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不合理！");
        return hostService.saveHost(host);
    }

    @CrossOrigin
    @RequestMapping(value = "/house/user/login", method = RequestMethod.POST)
    public ResponseBean hostLogin(@RequestBody Host host) {
        MyExceptionAssert.isNotBlank(host.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不能为空！");
        MyExceptionAssert.isNotBlank(host.getPassword(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "密码不能为空！");
        return hostService.login(host);
    }
//
//    @RequestMapping(value = "/house/publish/createHouse", method = RequestMethod.POST)
//    public ResponseBean createHouse(@RequestBody Host host) {
//        MyExceptionAssert.isNotBlank(host.getUserPhone(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房东账号不能为空！");
//        MyExceptionAssert.isNotBlank(host.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
//        return hostService.createOneHouse(host);
//    }
//

}
