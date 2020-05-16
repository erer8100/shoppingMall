package com.rhee.shoppingmall.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper()
public interface MainMenuMapper {
	List<MainMenuVO> getMainMenu();
	List<MainMenuVO> getSubMenu(String menuUrl);
	int getMenuLevel(String menuUrl);
	int countMenu(String menuUrl);
	
}
