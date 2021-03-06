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
import org.spring.springboot.service.HouseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            house.setBathroom(array_bathroom.toString());
            house.setDescription(array_electricAppliances.toString());
            house.setFacility(array_facility.toString());
            house.setClaim(array_claim.toString());

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

        if (houseDao.findByRoomId(json.getLong("roomId")) != null) {
            House house = houseDao.findByRoomId(json.getLong("roomId"));
            house.setTradingRules(json.getString("tradingRules"));
            house.setBreakContact(json.getString("breakContact"));
            house.setLeastDay(json.getLong("leastDay"));
            house.setMostDay(json.getLong("mostDay"));
            house.setReceiveOutside(json.getLong("receiveOutside"));
            house.setOtherRequirement(json.getString("otherRequirement"));
            house.setTip("tip");
            house.setHaveReviewed(0);
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
            house.setLiveWithOwner(json.getLong("liveWithOwner"));
            house.setReplaceBedSheet(json.getString("replaceBedSheet"));
            house.setBathroomType(json.getString("bathroomType"));
            house.setBed(json.getString("bed"));
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
    public ResponseBean saveImages(long roomId, String hostId, String picAll, String picOne) {
        ResponseBean responseBean = new ResponseBean();
        House house = houseDao.findByRoomId(roomId);
        if (house == null){
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("找不到id为" + roomId + "的房源");
        }else {
            if (!hostId.equals(house.getHostId())){
                responseBean.setCode(FAIL_CODE);
                responseBean.setMessage("保存失败，该房源不属于" + hostId + "房东的");
            }else {
                houseDao.saveImages(roomId, hostId, picAll, picOne);
                responseBean.setCode(SUCCESS_CODE);
                responseBean.setMessage("保存照片成功！");
            }
        }
        return responseBean;
    }

    @Override
    public ResponseBean getAllHouse(String str) {
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if ( json.getString("managementId").equals("Admin") && json.getString("password").equals("123456") ) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(houseDao.getHouseList(json.getLong("haveReviewed")));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getHouseByHostId(Host host){
        ResponseBean responseBean = new ResponseBean();
        if (host.getPhoneNumber() != null) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询房东所属房源信息成功");
            responseBean.setContent(houseDao.findHouseByHostId(host.getPhoneNumber()));
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
        if (json.getString("address") != null) {
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
        if (json.getString("title") != null) {
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
    public ResponseBean getHouseByHouseId(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if (json.getLong("id") != null) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("查询成功");
            responseBean.setContent(houseDao.findByRoomId(json.getLong("id")));
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean updateHouseReviewed(House house){
        ResponseBean responseBean = new ResponseBean();
        if (house.getId() != null && house.getHaveReviewed()==0 ) {
            house.setHaveReviewed(1);
            houseDao.updateReviewed(house);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("修改状态成功");
            responseBean.setContent("");
        } else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("修改状态失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean houseHaveReviewed(){
        ResponseBean responseBean = new ResponseBean();
        if ( houseDao.findHaveReviewedHouse() != null ) {
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("获取房源列表成功");
            JSONObject content = new JSONObject();
            content.put("list", houseDao.findHaveReviewedHouse());
            responseBean.setContent(content);
        }
        else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("获取房源列表失败");
            responseBean.setContent("");
        }
        return responseBean;
    }


    @Override
    public ResponseBean isReviewed(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if (json.getString("managementId").equals("Admin") && json.getString("password").equals("123456")){
            if(json.getLong("haveReviewed") == 1) {
                responseBean.setCode(SUCCESS_CODE);
                responseBean.setMessage("查询已审核房源列表成功");
                responseBean.setContent(houseDao.isReviewed(json.getLong("haveReviewed")));
            }
            if(json.getLong("haveReviewed") == 0){
                responseBean.setCode(SUCCESS_CODE);
                responseBean.setMessage("查询未审核房源列表成功");
                responseBean.setContent(houseDao.isReviewed(json.getLong("haveReviewed")));
            }
        }
        else
        {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("查询失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean HouseNotPass(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if ( houseDao.findByRoomId(json.getLong("roomId")) != null && json.getString("approveMessage") != null ){
            House house = houseDao.findByRoomId(json.getLong("roomId"));
            house.setApproveMessage(json.getString("approveMessage"));
            house.setHaveReviewed(-1);
            houseDao.updateApproveMessage(house);
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("完成设置审核不通过房源");
            responseBean.setContent("");
        }
        else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("审核不通过房源未作备注");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public ResponseBean RemoveOneHouse(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if(json.getLong("roomId")!=null){
            houseDao.RemoveHouse(json.getLong("roomId"));
            responseBean.setCode(SUCCESS_CODE);
            responseBean.setMessage("删除房源成功");
            responseBean.setContent( "被删除房源的ID："+json.getLong("roomId") );
        }
        else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("删除房源失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

    @Override
    public boolean isHouseIdExist(long id) {
        return houseDao.findByRoomId(id) != null;
    }

    @Override
    public House findHouseById(long id) {
        return houseDao.findByRoomId(id);
    }

    @Override
    public ResponseBean getTitle(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        responseBean.setCode(SUCCESS_CODE);
        responseBean.setMessage("查询成功");
        responseBean.setContent(houseDao.getHouseTitle(json.getLong("id")));
        return responseBean;
    }

    @Override
    public ResponseBean continueupdateInformation(String str){
        ResponseBean responseBean = new ResponseBean();
        JSONObject json = JSON.parseObject(str);
        if(json.getLong("id")!=null){
            Long type = houseDao.getType(json.getLong("id"));
            if(houseDao.getHaveReviewed(json.getLong("id"))<=0) {
                if (type <= 1)
                    responseBean.setContent(houseDao.getFirstPageInfor(json.getLong("id")));
                if (type == 2)
                    responseBean.setContent(houseDao.getSecondPageInfor(json.getLong("id")));
                if (type == 3)
                    responseBean.setContent(houseDao.getThirdPageInfor(json.getLong("id")));
                if (type == 4)
                    responseBean.setContent(houseDao.getForthPageInfor(json.getLong("id")));
                if (type == 5)
                    responseBean.setContent(houseDao.getFifthPageInfor(json.getLong("id")));
                responseBean.setCode(SUCCESS_CODE);
                responseBean.setMessage("获取修改发布房源信息成功");
            }
            else if(houseDao.getHaveReviewed(json.getLong("id"))==1) {
                responseBean.setContent(houseDao.getFifthPageInfor(json.getLong("id")));
                responseBean.setCode(SUCCESS_CODE);
                responseBean.setMessage("获取已发布房源信息成功");
            }
        }
        else {
            responseBean.setCode(FAIL_CODE);
            responseBean.setMessage("房源不存在，获取需要修改发布的房源信息失败");
            responseBean.setContent("");
        }
        return responseBean;
    }

}
