package org.spring.springboot.service;

import org.spring.springboot.domain.CheckInPeople;
import org.spring.springboot.domain.ResponseBean;

public interface CheckInPeopleService {
    ResponseBean createCheckInPeople(CheckInPeople checkInPeople);
    ResponseBean updateCheckInPeople(CheckInPeople checkInPeople);
    ResponseBean deleteCheckInPeople(int id);
    ResponseBean getAllPeoples(String belongUserId);
}
