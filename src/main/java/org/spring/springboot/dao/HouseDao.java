package org.spring.springboot.dao;

import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;

import java.util.List;

public interface HouseDao {

    House findByRoomId(Long id);

    int updateDescription(House house);
}
