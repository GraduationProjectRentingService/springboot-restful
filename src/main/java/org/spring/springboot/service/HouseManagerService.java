package org.spring.springboot.service;

import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;

public interface HouseManagerService {

    ResponseBean createOneHouse(String phoneNum);

    ResponseBean saveDescription(String str);

    ResponseBean saveFacilities(String str);

    ResponseBean savePriceRule(String str);

    ResponseBean saveRuleRequirement(String str);

    ResponseBean saveBaseInfo(String str);

    ResponseBean getAllHouse(User user);

    ResponseBean getHouseByHostId(String str);

    ResponseBean getHouseByAddress(String str);

    ResponseBean getHouseByTitle(String params);

    ResponseBean getHouseByHouseId(Long id);
}
