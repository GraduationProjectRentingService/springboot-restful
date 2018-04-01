package org.spring.springboot.controller.host;

import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.HostService;
import org.spring.springboot.service.HouseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "house")
public class HouseManagerController {

    @Autowired
    HostService hostService;
    @Autowired
    HouseManagerService houseManagerService;

    @CrossOrigin
    @RequestMapping(value = "publish/createHouse", method = RequestMethod.POST)
    public ResponseBean createHouse(@RequestBody Host host) {
        MyExceptionAssert.isNotBlank(host.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房东账号不能为空！");
        MyExceptionAssert.isNotBlank(host.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(hostService.findHostUserByPhoneNum(host.getPhoneNumber()) != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不存在！");
        MyExceptionAssert.isTrue(hostService.isHostUserTokenLegal(host.getPhoneNumber(), host.getToken()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
          return houseManagerService.createOneHouse(host.getPhoneNumber());
    }

    @CrossOrigin
    @RequestMapping(value = "publish/saveHouseDescription", method = RequestMethod.POST)
    public ResponseBean saveHouseDescription(@RequestBody String params){
            return houseManagerService.saveDescription(params);
    }

    @CrossOrigin
    @RequestMapping(value = "publish/saveHouseFacilities", method = RequestMethod.POST)
    public  ResponseBean saveHouseFacilities(@RequestBody String params){
        return houseManagerService.saveFacilities(params);
    }

    @CrossOrigin
    @RequestMapping(value = "publish/saveHousePriceRule", method = RequestMethod.POST)
    public  ResponseBean saveHousePriceRule(@RequestBody String params){
        return houseManagerService.savePriceRule(params);
    }

    @CrossOrigin
    @RequestMapping(value = "publish/saveHouseRuleRequirement", method = RequestMethod.POST)
    public  ResponseBean saveHouseRuleRequirement(@RequestBody String params){
        return houseManagerService.saveRuleRequirement(params);
    }

    @CrossOrigin
    @RequestMapping(value = "publish/saveHouseBaseInfo", method = RequestMethod.POST)
    public  ResponseBean saveHouseBaseInfo(@RequestBody String params){
        return houseManagerService.saveBaseInfo(params);
    }

    @CrossOrigin
    @RequestMapping(value = "getAllHouseList", method = RequestMethod.POST)
    public  ResponseBean getAllHouseList(@RequestBody String params){
        return houseManagerService.getAllHouse(params);
    }

    @CrossOrigin
    @RequestMapping(value = "publish/getHouseListByHostId", method = RequestMethod.POST)
    public  ResponseBean getHouseListByHostId(@RequestBody String params){
        return houseManagerService.getHouseByHostId(params);
    }

    @CrossOrigin
    @RequestMapping(value = "publish/getHouseListByAddress", method = RequestMethod.POST)
    public  ResponseBean getHouseListByAddress(@RequestBody String params){
        return houseManagerService.getHouseByAddress(params);
    }

    @CrossOrigin
    @RequestMapping(value = "publish/getHouseListByTitle", method = RequestMethod.POST)
    public  ResponseBean getHouseListByTitle(@RequestBody String params){
        return houseManagerService.getHouseByTitle(params);
    }

    @CrossOrigin
    @RequestMapping(value = "publish/getHouseListByHouseId", method = RequestMethod.POST)
    public  ResponseBean getHouseListByHouseId(@RequestBody String params){
        return houseManagerService.getHouseByHouseId(params);
    }

    //修改房源审核状态
    @CrossOrigin
    @RequestMapping(value = "updateHousehaveReviewed", method = RequestMethod.POST)
    public  ResponseBean updateHousehaveReviewed(@RequestBody House house){
        return houseManagerService.updateHouseReviewed(house);
    }

    //用户获取房源列表
    @CrossOrigin
    @RequestMapping(value = "getHousehaveReviewed", method = RequestMethod.POST)
    public  ResponseBean getHousehaveReviewed(@RequestBody String params){
        return houseManagerService.houseHaveReviewed(params);
    }

    //测试
    @CrossOrigin
    @RequestMapping(value = "getHouseTitle", method = RequestMethod.POST)
    public  ResponseBean getHouseTitle(@RequestBody String params){
        return houseManagerService.getTitle(params);
    }


}
