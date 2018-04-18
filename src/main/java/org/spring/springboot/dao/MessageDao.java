package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.Message;

import java.util.List;

public interface MessageDao {

    int saveMessage(Message message);

    List<Message> getAll();
}
