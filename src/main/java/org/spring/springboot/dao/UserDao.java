package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAllUser();

    User findByPhone(String phone_num);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);

    int updateToken(@Param("phoneNum") String phoneNum, @Param("token") String token);
}
