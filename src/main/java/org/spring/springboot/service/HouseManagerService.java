package org.spring.springboot.service;

import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.ResponseBean;

public interface HouseManagerService {
    ResponseBean createHouse(String phoneNum);
}
