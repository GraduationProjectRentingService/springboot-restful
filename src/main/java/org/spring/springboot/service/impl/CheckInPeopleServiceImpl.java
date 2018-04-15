package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.spring.springboot.dao.CheckInPeopleDao;
import org.spring.springboot.domain.CheckInPeople;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.CheckInPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckInPeopleServiceImpl implements CheckInPeopleService {

    @Autowired
    CheckInPeopleDao checkInPeopleDao;

    @Override
    public ResponseBean createCheckInPeople(CheckInPeople checkInPeople) {
        if (checkInPeopleDao.findByBelongUserIdAndIdCard(checkInPeople.getBelongUserId(), checkInPeople.getIdCard())!= null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "该身份证的入住人信息已存在！", "");
        }

        checkInPeopleDao.createCheckInPeople(checkInPeople);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "新增入住人信息成功！", "");
    }

    @Override
    public ResponseBean updateCheckInPeople(CheckInPeople checkInPeople) {
        CheckInPeople find = checkInPeopleDao.findByBelongUserIdAndIdCard(checkInPeople.getBelongUserId(), checkInPeople.getIdCard());
        if (find == null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "更新失败！不存在该入住人信息！","");
        }

        checkInPeopleDao.updateCheckInPeople(find);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "修改成功！", "");
    }

    @Override
    public ResponseBean deleteCheckInPeople(int id) {
        CheckInPeople find = checkInPeopleDao.findById(id);
        if (find == null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "删除失败！不存在该入住人信息！","");
        }
        checkInPeopleDao.deleteCheckInPeople(id);

        return new ResponseBean(ResponseBean.SUCCESS_CODE, "删除成功！", "");
    }

    @Override
    public ResponseBean getAllPeoples(String belongUserId) {
        JSONObject content = new JSONObject();
        content.put("list", checkInPeopleDao.findAllByBelongUserId(belongUserId));
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "获取入住人列表成功", content);
    }
}
