package org.spring.springboot.service;

import org.spring.springboot.domain.User;
import org.spring.springboot.domain.ResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/9.
 */
public interface UserService {

    /**
     * 获取用户信息列表
     *
     * @return
     */
    List<User> findAllUser();

    /**
     * 根据 ID,查询信息
     *
     * @param user
     * @return
     */
    ResponseBean login(User user);

    /**
     * 新增信息
     *
     * @param user
     * @return
     */
    ResponseBean saveUser(User user);

    /**
     * 更新信息
     *
     * @param user
     * @return
     */
    ResponseBean updateUser(User user);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    ResponseBean deleteUser(Long id);
}
