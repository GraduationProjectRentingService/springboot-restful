package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;

import java.util.List;

public interface HouseDao {

    long createHouse(@Param("hostUserPhone") String hostUserPhone);

    House findByRoomId(Long id);

    int updateDescription(House house);
}
