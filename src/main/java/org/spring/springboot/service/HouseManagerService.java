package org.spring.springboot.service;

import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;

public interface HouseManagerService {

    ResponseBean createOneHouse(String phoneNum);

    ResponseBean saveDescription(String str);

    ResponseBean saveFacilities(String str);

    ResponseBean savePriceRule(String str);

    ResponseBean saveRuleRequirement(String str);

    ResponseBean saveBaseInfo(String str);

    ResponseBean saveImages(long roomId, String hostId, String picAll, String picOne);

    ResponseBean getAllHouse(String str);

    ResponseBean getHouseByHostId(Host str);

    ResponseBean getHouseByAddress(String str);

    ResponseBean getHouseByTitle(String params);

    ResponseBean getHouseByHouseId(String str);

    ResponseBean updateHouseReviewed(House house);

    ResponseBean houseHaveReviewed();

    ResponseBean getTitle(String params);

    ResponseBean isReviewed(String params);

    ResponseBean HouseNotPass(String str);

    ResponseBean RemoveOneHouse(String str);

    boolean isHouseIdExist(long id);

    House findHouseById(long id);
}
