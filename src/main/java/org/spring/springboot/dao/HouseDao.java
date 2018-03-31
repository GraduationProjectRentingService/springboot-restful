package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;

import java.util.List;

public interface HouseDao {

    long createAnHouse(House house);

    House findByRoomId(Long id);

    int updateDescription(House house);

    int updateFacilities(House house);

    int updatePriceRule(House house);

    int updateRuleRequirement(House house);

    int updateBaseInfo(House house);

    long FindMaxRoomId();

    List<House> getHouseList();

    List<House> findHouseByHostId(String str);

    List<House> findHouseByAddress(String str);

    List<House> findHouseByTitle(String str);
}
