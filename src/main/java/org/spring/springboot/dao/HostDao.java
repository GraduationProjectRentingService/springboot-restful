package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.House;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.Host;

import java.util.List;

public interface HostDao {

    List<Host> findAllUesr();

    Host findByPhone(String phone_num);

    House findByRoomId(Long id);

    int saveHost(Host host);

    ResponseBean updateHost(Host host);

    ResponseBean deleteHost(Long id);

    int updateToken(@Param("phoneNum") String phoneNum, @Param("token") String token);

}
