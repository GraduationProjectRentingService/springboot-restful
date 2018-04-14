package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.House;
import sun.rmi.runtime.Log;

import java.util.List;

public interface HouseDao {

    long createAnHouse(House house);

    House findByRoomId(Long id);

    int updateDescription(House house);

    int updateFacilities(House house);

    int updatePriceRule(House house);

    int updateRuleRequirement(House house);

    int updateBaseInfo(House house);

    int saveImages(@Param("houseId") long houseId, @Param("hostId") String hostId, @Param("picAll") String picAll, @Param("picOne") String picOne);

    long FindMaxRoomId();

    List<House> getHouseList(Long haveReviewed);

    List<House> findHouseByHostId(String str);

    List<House> findHouseByAddress(String str);

    List<House> findHouseByTitle(String str);

    List<House> findHaveReviewedHouse();

    int updateReviewed(House house);

    List<House> findTypeHouse(Long type);

    String getHouseTitle(Long houseId);

    String getHostId(Long houseId);

    List<House> isReviewed(Long haveReviewed);

    int updateApproveMessage(House approveMessage);

    int RemoveHouse(Long house);
}
