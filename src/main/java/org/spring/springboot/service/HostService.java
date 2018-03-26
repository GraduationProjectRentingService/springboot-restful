package org.spring.springboot.service;

import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2018/2/9.
 */
public interface HostService {

    /**
     * 获取房东信息列表
     *
     * @return
     */
    List<User> findAllUser();

    /**
     * 根据 ID,查询信息
     *
     * @param host
     * @return
     */
    ResponseBean login(Host host);

    /**
     * 新增信息
     *
     * @param host
     * @return
     */
    ResponseBean saveHost(Host host);

    /**
     * 更新信息
     *
     * @param host
     * @return
     */
    ResponseBean updateHost(Host host);

    /**
     * 根据 ID,删除信息
     *
     * @param host
     * @return
     */
    ResponseBean delete(Host host);

    ResponseBean deleteHost(Long id);

    ResponseBean createOneHouse();
    ResponseBean saveDescription(String str);
}
