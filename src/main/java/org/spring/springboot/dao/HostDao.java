package org.spring.springboot.dao;

import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.Host;

import java.util.List;

public interface HostDao {

    List<Host> findAllUesr();

    Host findByPhone(String phone_num);

    int saveHost(Host host);

    ResponseBean updateHost(Host host);

    ResponseBean deleteHost(Long id);

}
