package org.spring.springboot.controller.host;

import org.spring.springboot.domain.Host;
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

    @RequestMapping(value = "publish/createHouse", method = RequestMethod.POST)
    public ResponseBean createHouse(@RequestBody Host host) {
        MyExceptionAssert.isNotBlank(host.getPhoneNumber(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房东账号不能为空！");
        MyExceptionAssert.isNotBlank(host.getToken(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(hostService.findHostUserByPhoneNum(host.getPhoneNumber()) != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不存在！");
        MyExceptionAssert.isTrue(hostService.isHostUserTokenLegal(host.getPhoneNumber(), host.getToken()), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
          return houseManagerService.createOneHouse(host.getPhoneNumber());
    }

        @RequestMapping(value = "publish/saveHouseDescription", method = RequestMethod.POST)
        public ResponseBean saveHouseDescription(@RequestBody String params){
            //System.out.print(params);
            return houseManagerService.saveDescription(params);
    }

    @RequestMapping(value = "publish/saveHouseFacilities", method = RequestMethod.POST)
    public  ResponseBean saveHouseFacilities(@RequestBody String params){
        return houseManagerService.saveFacilities(params);
    }

    @RequestMapping(value = "publish/saveHousePriceRule", method = RequestMethod.POST)
    public  ResponseBean saveHousePriceRule(@RequestBody String params){
        return houseManagerService.savePriceRule(params);
    }

    @RequestMapping(value = "publish/saveHouseRuleRequirement", method = RequestMethod.POST)
    public  ResponseBean saveHouseRuleRequirement(@RequestBody String params){
        return houseManagerService.saveRuleRequirement(params);
    }

    @RequestMapping(value = "publish/saveHouseBaseInfo", method = RequestMethod.POST)
    public  ResponseBean saveHouseBaseInfo(@RequestBody String params){
        return houseManagerService.saveBaseInfo(params);
    }

    @RequestMapping(value = "publish/getAllHouseList", method = RequestMethod.POST)
    public  ResponseBean getAllHouseList(@RequestBody User user){
        return houseManagerService.getAllHouse(user);
    }

    @RequestMapping(value = "publish/getHouseListByHostId", method = RequestMethod.POST)
    public  ResponseBean getHouseListByHostId(@RequestBody String params){
        return houseManagerService.getHouseByHostId(params);
    }

    @RequestMapping(value = "publish/getHouseListByAddress", method = RequestMethod.POST)
    public  ResponseBean getHouseListByAddress(@RequestBody String params){
        return houseManagerService.getHouseByAddress(params);
    }

    @RequestMapping(value = "publish/getHouseListByTitle", method = RequestMethod.POST)
    public  ResponseBean getHouseListByTitle(@RequestBody String params){
        return houseManagerService.getHouseByTitle(params);
    }

    @RequestMapping(value = "publish/getHouseListByHouseId", method = RequestMethod.POST)
    public  ResponseBean getHouseListByHouseId(@RequestBody Long params){
        return houseManagerService.getHouseByHouseId(params);
    }

}
