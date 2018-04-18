package org.spring.springboot.controller.user;

import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.dto.CheckInPeopleDto;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.CheckInPeopleService;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("checkInPeople")
public class CheckInPeopleController {

    @Autowired
    UserService userService;
    @Autowired
    CheckInPeopleService checkInPeopleService;

    private void checkUser(CheckInPeopleDto checkInPeopleDto){
        MyExceptionAssert.isNotBlank(checkInPeopleDto.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不能为空！");
        MyExceptionAssert.isNotBlank(checkInPeopleDto.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
//        MyExceptionAssert.isTrue(userService.isUserTokenLegal(checkInPeopleDto.getPhoneNumber(), checkInPeopleDto.getToken()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
        checkInPeopleDto.setBelongUserId(checkInPeopleDto.getPhoneNumber());//设置入住人的归属账号
    }

    @RequestMapping("create")
    public ResponseBean create(@RequestBody CheckInPeopleDto checkInPeopleDto){
        checkUser(checkInPeopleDto);
        if (!userService.isUserTokenLegal(checkInPeopleDto.getPhoneNumber(), checkInPeopleDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token过期，请重新登录！", "");
        }
        return checkInPeopleService.createCheckInPeople(checkInPeopleDto);
    }

    @RequestMapping("update")
    public ResponseBean update(@RequestBody CheckInPeopleDto checkInPeopleDto){
        checkUser(checkInPeopleDto);
        if (!userService.isUserTokenLegal(checkInPeopleDto.getPhoneNumber(), checkInPeopleDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token过期，请重新登录！", "");
        }
        return checkInPeopleService.updateCheckInPeople(checkInPeopleDto);
    }

    @RequestMapping("getAll")
    public ResponseBean getAll(@RequestBody CheckInPeopleDto checkInPeopleDto){
        checkUser(checkInPeopleDto);
        if (!userService.isUserTokenLegal(checkInPeopleDto.getPhoneNumber(), checkInPeopleDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token过期，请重新登录！", "");
        }
        return checkInPeopleService.getAllPeoples(checkInPeopleDto.getPhoneNumber());
    }

    @RequestMapping("delete")
    public ResponseBean delete(@RequestBody CheckInPeopleDto checkInPeopleDto){
        checkUser(checkInPeopleDto);
        if (!userService.isUserTokenLegal(checkInPeopleDto.getPhoneNumber(), checkInPeopleDto.getToken())){
            return new ResponseBean(ResponseBean.TOKEN_ILLEGAL_CODE, "token过期，请重新登录！", "");
        }
        MyExceptionAssert.isTrue(checkInPeopleDto.getId() > 0, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房源id有误！");
        return checkInPeopleService.deleteCheckInPeople(checkInPeopleDto.getId());
    }
}
