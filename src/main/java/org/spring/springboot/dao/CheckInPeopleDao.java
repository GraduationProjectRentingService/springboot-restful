package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.CheckInPeople;

import java.util.List;

public interface CheckInPeopleDao {
    int createCheckInPeople(CheckInPeople checkInPeople);
    CheckInPeople findById(@Param("id") int id);
    CheckInPeople findByBelongUserIdAndIdCard(@Param("belongUserId") String userId, @Param("idCard") String idCard);
    List<CheckInPeople> findAllByBelongUserId(@Param("belongUserId") String belongUserId);
    int updateCheckInPeople(CheckInPeople checkInPeople);
    int deleteCheckInPeople(@Param("id") int id);
}
