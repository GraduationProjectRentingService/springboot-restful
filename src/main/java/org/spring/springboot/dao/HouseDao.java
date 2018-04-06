package org.spring.springboot.dao;

import org.spring.springboot.domain.House;

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

    List<House> getHouseList(Long haveReviewed);

    List<House> findHouseByHostId(String str);

    List<House> findHouseByAddress(String str);

    List<House> findHouseByTitle(String str);

    List<House> findHaveReviewedHouse(Long type);

    int updateReviewed(House house);

    List<House> findTypeHouse(Long type);

    String getHouseTitle(Long houseId);

    String getHostId(Long houseId);

    List<House>  isReviewed(Long haveReviewed);
}
