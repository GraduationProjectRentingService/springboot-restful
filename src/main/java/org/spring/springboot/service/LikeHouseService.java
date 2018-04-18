package org.spring.springboot.service;

import org.spring.springboot.domain.ResponseBean;

public interface LikeHouseService {
    ResponseBean addHouseToLikes(String phone, int houseId);//添加收藏房源到收藏列表中
    ResponseBean removeHouseFromLikes(String phone, int houseId);//将某个房源从房源列表中移除
    ResponseBean getAllHouses(String phone);//获取整个收藏列表
    ResponseBean houseHasLiked(String phone, int houseId);//查看某个房源是否已经添加到收藏列表中
}
