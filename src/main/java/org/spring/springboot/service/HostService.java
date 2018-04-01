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

    ResponseBean deleteHost(Long id);

    ResponseBean createOneHouse(Host host);

    /**
     * 通过手机号码查寻房东实例
     * @param phoneNum
     * @return
     */
    Host findHostUserByPhoneNum(String phoneNum);

    /**
     * 验证房东Token是否有效
     * @param phoneNum
     * @param token
     * @return
     */
    boolean isHostUserTokenLegal(String phoneNum, String token);

    ResponseBean getAllHost(String params);
}
