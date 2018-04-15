package org.spring.springboot.controller.host;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    // TODO: 2018/4/15 保存图片
    @CrossOrigin
    @RequestMapping(value = "publish/saveImages", method = RequestMethod.POST)
    public ResponseBean saveImages(@RequestBody String params){
        JSONObject jsonObject = JSON.parseObject(params);
        String hostPhone = jsonObject.getString("phoneNumber");
        String token = jsonObject.getString("token");
        MyExceptionAssert.isNotBlank(hostPhone, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "房东账号不能为空！");
        MyExceptionAssert.isNotBlank(token, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "token不能为空！");
        MyExceptionAssert.isTrue(hostService.findHostUserByPhoneNum(hostPhone) != null, MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "该账号不存在！");
        MyExceptionAssert.isTrue(hostService.isHostUserTokenLegal(hostPhone, token), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "Token过期，请重新登录！");
        long roomId = jsonObject.getLong("roomId");
        String picAll = jsonObject.getString("picAll");
        String picOne = jsonObject.getString("picOne");
        return houseManagerService.saveImages(roomId, hostPhone, picAll, picOne);
    }

    @CrossOrigin
    @RequestMapping(value = "getAllHouseList", method = RequestMethod.POST)
    public  ResponseBean getAllHouseList(@RequestBody String params){
        return houseManagerService.getAllHouse(params);
    }

    //房东管理·房东获取房源列表
    @CrossOrigin
    @RequestMapping(value = "getHouseListByHostId", method = RequestMethod.POST)
    public  ResponseBean getHouseListByHostId(@RequestBody Host host){
        return houseManagerService.getHouseByHostId(host);
    }

    //修改房源审核状态
    @CrossOrigin
    @RequestMapping(value = "updateHousehaveReviewed", method = RequestMethod.POST)
    public  ResponseBean updateHousehaveReviewed(@RequestBody House house){
        return houseManagerService.updateHouseReviewed(house);
    }

    //房源审核不通过
    @CrossOrigin
    @RequestMapping(value = "HouseNoPass", method = RequestMethod.POST)
    public  ResponseBean HouseNoPass(@RequestBody String params){
        return houseManagerService.HouseNotPass(params);
    }

    //管理员获取未审核房源列表
    @CrossOrigin
    @RequestMapping(value = "HouseisReviewed", method = RequestMethod.POST)
    public  ResponseBean HouseisReviewed(@RequestBody String params){
        return houseManagerService.isReviewed(params);
    }

    //测试
    @CrossOrigin
    @RequestMapping(value = "getHouseTitle", method = RequestMethod.POST)
    public  ResponseBean getHouseTitle(@RequestBody String params){
        return houseManagerService.getTitle(params);
    }

    //用户获取房源列表
    @CrossOrigin
    @RequestMapping(value = "user/getHousehaveReviewed", method = RequestMethod.POST)
    public  ResponseBean getHousehaveReviewed(){
        return houseManagerService.houseHaveReviewed();
    }

    //用户根据房源地址搜索
    @CrossOrigin
    @RequestMapping(value = "user/getHouseListByAddress", method = RequestMethod.POST)
    public  ResponseBean getHouseListByAddress(@RequestBody String params){
        return houseManagerService.getHouseByAddress(params);
    }

    //用户根据房源标题搜索
    @CrossOrigin
    @RequestMapping(value = "user/getHouseListByTitle", method = RequestMethod.POST)
    public  ResponseBean getHouseListByTitle(@RequestBody String params){
        return houseManagerService.getHouseByTitle(params);
    }

    @CrossOrigin
    @RequestMapping(value = "getHouseListByHouseId", method = RequestMethod.POST)
    public  ResponseBean getHouseListByHouseId(@RequestBody String params){
        return houseManagerService.getHouseByHouseId(params);
    }

    //房东删除房源
    @CrossOrigin
    @RequestMapping(value = "hostRemoveOneHouse", method = RequestMethod.POST)
    public  ResponseBean hostRemoveOneHouse(@RequestBody String params){
        return houseManagerService.RemoveOneHouse(params);
    }

}
