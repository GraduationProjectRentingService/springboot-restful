package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.dao.HouseDao;
import org.spring.springboot.dao.LikeHouseDao;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.LikeHouseBean;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.LikeHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeHouseServiceImpl implements LikeHouseService {

    @Autowired
    LikeHouseDao likeHouseDao;
    @Autowired
    HouseDao houseDao;

    @Override
    public ResponseBean addHouseToLikes(String phone, int houseId) {
        if (houseDao.findByRoomId((long) houseId) == null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "收藏的房源不存在！", "");
        }

        LikeHouseBean bean = new LikeHouseBean();
        bean.setHouseId(houseId);
        bean.setUserPhone(phone);
        if (likeHouseDao.find(bean) != null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "收藏的房源已存在！", "");
        }

        likeHouseDao.add(bean);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "收藏成功！", "");
    }

    @Override
    public ResponseBean removeHouseFromLikes(String phone, int houseId) {
        if (houseDao.findByRoomId((long) houseId) == null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "收藏的房源不存在！", "");
        }

        LikeHouseBean bean = new LikeHouseBean();
        bean.setHouseId(houseId);
        bean.setUserPhone(phone);
        likeHouseDao.remove(bean);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "取消收藏成功！", "");
    }

    @Override
    public ResponseBean getAllHouses(String phone) {
        List<LikeHouseBean> likeHouseBeans = likeHouseDao.findAllByPhone(phone);
        if (likeHouseBeans == null || likeHouseBeans.size() == 0){
            return new ResponseBean(ResponseBean.FAIL_CODE, "收藏列表为空！", "");
        }

        List<House> list = new ArrayList<>();
        for (LikeHouseBean bean: likeHouseBeans){
            list.add(houseDao.findByRoomId((long) bean.getHouseId()));
        }
        JSONObject content = new JSONObject();
        content.put("list", list);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "获取收藏列表成功！", content);
    }

    @Override
    public ResponseBean houseHasLiked(String phone, int houseId) {
        LikeHouseBean bean = new LikeHouseBean();
        bean.setUserPhone(phone);
        bean.setHouseId(houseId);
        JSONObject content = new JSONObject();
        if (likeHouseDao.find(bean) == null){
            content.put("isLike", false);
            return new ResponseBean(ResponseBean.SUCCESS_CODE, "还未收藏！", content);
        }else {
            content.put("isLike", true);
            return new ResponseBean(ResponseBean.SUCCESS_CODE, "已收藏！", content);
        }
    }
}
