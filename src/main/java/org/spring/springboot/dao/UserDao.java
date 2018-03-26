package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAllUesr();

    User findByPhone(String phone_num);

    int saveUser(User user);

    ResponseBean updateUser(User user);

    ResponseBean deleteUser(Long id);

    int updateToken(String s);
}
