package org.spring.springboot.service;

import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;

public interface HouseManagerService {

    ResponseBean createOneHouse(String phoneNum);

    ResponseBean saveDescription(String str);

    ResponseBean saveFacilities(String str);

    ResponseBean savePriceRule(String str);

    ResponseBean saveRuleRequirement(String str);

    ResponseBean saveBaseInfo(String str);

    ResponseBean getAllHouse(String str);

    ResponseBean getHouseByHostId(String str);

    ResponseBean getHouseByAddress(String str);

    ResponseBean getHouseByTitle(String params);

    ResponseBean getHouseByHouseId(String str);

    ResponseBean updateHouseReviewed(House house);

    ResponseBean houseHaveReviewed(String str);

    ResponseBean getTitle(String params);

}
