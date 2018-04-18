package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.LikeHouseBean;

import java.util.List;

public interface LikeHouseDao {
    LikeHouseBean find(LikeHouseBean bean);//查看存不存在
    List<LikeHouseBean> findAllByPhone(@Param("userPhone") String userPhone);//获取某个用户的所有收藏列表
    int remove(LikeHouseBean bean);//删除
    int add(LikeHouseBean bean);//添加
}
