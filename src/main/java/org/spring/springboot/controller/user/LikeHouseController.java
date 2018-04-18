package org.spring.springboot.controller.user;

import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.dto.BaseDto;
import org.spring.springboot.dto.IBaseDto;
import org.spring.springboot.dto.LikeHouseDto;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.LikeHouseService;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("like")
public class LikeHouseController {

    @Autowired
    UserService userService;
    @Autowired
    LikeHouseService likeHouseService;

    /**
     * 检查用户是否登录
     * @param dto
     */
    private void checkUser(IBaseDto dto){
        MyExceptionAssert.isNotBlank(dto.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "账号不能为空！");
        MyExceptionAssert.isNotBlank(dto.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(userService.getUserInfo(dto.getPhoneNumber()) != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不存在！");
    }

    @RequestMapping(value = "addHouse", method = RequestMethod.POST)
    public ResponseBean addHouse(@RequestBody LikeHouseDto dto){
        checkUser(dto);
        return likeHouseService.addHouseToLikes(dto.getPhoneNumber(), dto.getHouseId());
    }

    @RequestMapping(value = "deleteHouse", method = RequestMethod.POST)
    public ResponseBean deleteHouse(@RequestBody LikeHouseDto dto){
        checkUser(dto);
        return likeHouseService.removeHouseFromLikes(dto.getPhoneNumber(), dto.getHouseId());
    }

    @RequestMapping(value = "houseHasLiked", method = RequestMethod.POST)
    public ResponseBean houseHasLiked(@RequestBody LikeHouseDto dto){
        checkUser(dto);
        return likeHouseService.houseHasLiked(dto.getPhoneNumber(), dto.getHouseId());
    }

    @RequestMapping(value = "getAll", method = RequestMethod.POST)
    public ResponseBean getAll(@RequestBody BaseDto dto){
        checkUser(dto);
        return likeHouseService.getAllHouses(dto.getPhoneNumber());
    }
}
