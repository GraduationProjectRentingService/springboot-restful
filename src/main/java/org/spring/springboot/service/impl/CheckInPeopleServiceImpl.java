package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.CheckInPeopleDao;
import org.spring.springboot.domain.CheckInPeople;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.exception.MyExceptionAssert;
import org.spring.springboot.exception.MyExceptionCode;
import org.spring.springboot.service.CheckInPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CheckInPeopleServiceImpl implements CheckInPeopleService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CheckInPeopleDao checkInPeopleDao;

    @Override
    public ResponseBean createCheckInPeople(CheckInPeople checkInPeople) {
        MyExceptionAssert.isNotBlank(checkInPeople.getIdCard(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "身份证不能空！");
        MyExceptionAssert.isNotBlank(checkInPeople.getName(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "姓名不能为空！");
        MyExceptionAssert.isNotBlank(checkInPeople.getPhone(), MyExceptionCode.PARAM_REQUIRED_EXCEPTION, "手机号码不能为空！");
        if (checkInPeopleDao.findByBelongUserIdAndIdCard(checkInPeople.getBelongUserId(), checkInPeople.getIdCard())!= null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "该身份证的入住人信息已存在！", "");
        }

        checkInPeopleDao.createCheckInPeople(checkInPeople);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "新增入住人信息成功！", "");
    }

    @Override
    public ResponseBean updateCheckInPeople(CheckInPeople checkInPeople) {
        CheckInPeople find = checkInPeopleDao.findById(checkInPeople.getId());
        if (find == null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "更新失败！不存在该入住人信息！","");
        }

        if (checkInPeopleDao.findByBelongUserIdAndIdCard(checkInPeople.getBelongUserId(), checkInPeople.getIdCard()) != null){
            //说明身份证已存在数据库中
            return new ResponseBean(ResponseBean.FAIL_CODE, "更新失败！已经存在该身份证的入住信息！", "");
        }

        checkInPeopleDao.updateCheckInPeople(checkInPeople);
        return new ResponseBean(ResponseBean.SUCCESS_CODE, "修改成功！", "");
    }

    @Override
    public ResponseBean deleteCheckInPeople(int id) {
        CheckInPeople find = checkInPeopleDao.findById(id);
        if (find == null){
            return new ResponseBean(ResponseBean.FAIL_CODE, "删除失败！不存在该入住人信息！","");
        }
        if (find.isUse() == 1){
            return new ResponseBean(ResponseBean.FAIL_CODE, "删除失败！该入住人信息在使用中！", "");
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
