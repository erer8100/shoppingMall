package com.rhee.shoppingmall.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.rhee.shoppingmall.user.MenuVO;

@Mapper
public interface CommonMapper {
	List<MenuVO> getMenuInfo(String menuId);
	String getMenuId(String screenName);
	List<Map<String, Object>> getUrlList(String screenName);
	List<MenuVO> getMenuList(String screenName);
	List<Map<String, Object>> getAllUrlList();
}
