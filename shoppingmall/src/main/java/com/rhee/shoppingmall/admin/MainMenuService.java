package com.rhee.shoppingmall.admin;

import java.util.List;

public interface MainMenuService {
	List<MainMenuVO> getMainMenu() throws Exception;
	List<MainMenuVO> getSubMenu(String menuUrl) throws Exception;
}
