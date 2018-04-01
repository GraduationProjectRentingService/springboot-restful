package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.HouseDao;
import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;
import org.spring.springboot.exception.ExceptionResolver;
import org.spring.springboot.service.HouseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.spring.springboot.domain.ResponseBean.FAIL_CODE;
import static org.spring.springboot.domain.ResponseBean.SUCCESS_CODE;

@Service
public class HouseManagerServiceImpl implements HouseManagerService {
    final Logger log = LoggerFactory.getLogger(HouseManagerServiceImpl.class);

    @Autowired
    HouseDao houseDao;
    @Autowired
    private UserDao userDao;

    @Override
    public ResponseBean createOneHouse(String phoneNum) {
        long id = (houseDao.FindMaxRoomId()) + 1;
        House house = new House();
        house.setId(id);
        house.setHostId(phoneNum);
        houseDao.createAnHouse(house);
        log.info("create house: " + id);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "创建房源成功！", id);
    }

    @Override
    public ResponseBean saveDescription(String str) {
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if (houseDao.findByRoomId(json.getLong("roomId")) != null) {
            House house = houseDao.findByRoomId(json.getLong("roomId"));
            house.setHostId(json.getString("userPhone"));
            house.setTitle(json.getString("title"));
            house.setDescription(json.getString("description"));
            house.setHouseInfo(json.getString("houseInfo"));
            house.setTrafficCondition(json.getString("trafficCondition"));
            house.setSurroundCondition(json.getString("surroundCondition"));
            houseDao.updateDescription(house);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("添加成功");
            responseBean.setContent("");
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("添加失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean saveFacilities(String str) {
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        JSONArray array_bathroom = json.getJSONArray("bathroom");
        JSONArray array_electricAppliances = json.getJSONArray("electricAppliances");
        JSONArray array_facility = json.getJSONArray("facility");
        JSONArray array_claim = json.getJSONArray("claim");

        if (houseDao.findByRoomId(json.getLong("roomId")) != null) {
            House house = houseDao.findByRoomId(json.getLong("roomId"));
            house.setHostId(json.getString("userPhone"));
            house.setBathroom(array_bathroom.getString(0) + "," + array_bathroom.getString(1));
            house.setDescription(array_electricAppliances.getString(0) + "," + array_electricAppliances.getString(1));
            house.setFacility(array_facility.getString(0) + "," + array_facility.getString(1));
            house.setClaim(array_claim.getString(0) + "," + array_claim.getString(1));

            houseDao.updateFacilities(house);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("添加成功");
            responseBean.setContent("");
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("添加失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean savePriceRule(String str) {
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);

        if (houseDao.findByRoomId(json.getLong("roomId")) != null) {
            House house = houseDao.findByRoomId(json.getLong("roomId"));
            house.setDailyPrice(json.getLong("dailyPrice"));
            house.setDeposit(json.getLong("deposit"));
            house.setAddGuest(json.getLong("addGuest"));
            house.setOtherPrice(json.getString("otherPrice"));
            houseDao.updatePriceRule(house);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("添加成功");
            responseBean.setContent("");
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("添加失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean saveRuleRequirement(String str) {
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        JSONArray array_breakContact = json.getJSONArray("breakContact");

        if (houseDao.findByRoomId(json.getLong("roomId")) != null) {
            House house = houseDao.findByRoomId(json.getLong("roomId"));
            house.setTradingRules(json.getString("tradingRules"));
            house.setBreakContact(array_breakContact.getString(0) + "," + array_breakContact.getString(1));
            house.setLeastDay(json.getLong("leastDay"));
            house.setMostDay(json.getLong("mostDay"));
            house.setReceiveOutside(json.getLong("receiveOutside"));
            house.setOtherRequirement(json.getString("otherRequirement"));
            house.setTip("tip");
            houseDao.updateRuleRequirement(house);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("添加成功");
            responseBean.setContent("");
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("添加失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean saveBaseInfo(String str) {
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        JSONArray array_bed = json.getJSONArray("bed");
        if (houseDao.findByRoomId(json.getLong("roomId")) != null) {
            House house = houseDao.findByRoomId(json.getLong("roomId"));
            house.setAddress(json.getString("address"));
            house.setRentalType(json.getLong("rentalType"));
            house.setRentalTypeText(json.getString("rentalTypeText"));
            house.setPeopleNum(json.getLong("peopleNum"));
            house.setRoomNum(json.getLong("roomNum"));
            house.setHallNum(json.getLong("hallNum"));
            house.setBathroomNum(json.getLong("bathroomNum"));
            house.setKitchenNum(json.getLong("kitchenNum"));
            house.setBalconyNum(json.getLong("balconyNum"));
            house.setRoomArea(json.getLong("roomArea"));
            house.setSofaNum(json.getLong("sofaNum"));
            house.setLiveWithOwner(json.getLong("liveWithOwner"));
            house.setReplaceBedSheet(json.getString("replaceBedSheet"));
            house.setBed(array_bed.getString(0) + "," + array_bed.getString(1));
            house.setTip("tip");
            houseDao.updateBaseInfo(house);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("添加成功");
            responseBean.setContent("");
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("添加失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getAllHouse(User user) {
        ResponseBean responseBean = new ResponseBean();
        if (userDao.findByPhone(user.getPhoneNumber()) != null) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(houseDao.getHouseList());
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getHouseByHostId(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if (userDao.findByPhone(json.getString("phoneNumber")) != null) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(houseDao.findHouseByHostId(json.getString("hostPhoneNumber")));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getHouseByAddress(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if (userDao.findByPhone(json.getString("phoneNumber")) != null) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(houseDao.findHouseByAddress(json.getString("address")));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getHouseByTitle(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if (userDao.findByPhone(json.getString("phoneNumber")) != null) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(houseDao.findHouseByTitle(json.getString("title")));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getHouseByHouseId(Long id){
        ResponseBean responseBean = new ResponseBean();
        if (houseDao.findByRoomId(id) != null) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(houseDao.findByRoomId(id));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }
}