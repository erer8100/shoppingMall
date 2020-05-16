package com.rhee.shoppingmall.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhee.shoppingmall.ParamVO;
import com.rhee.shoppingmall.user.MenuVO;

@Service
public class CommonUtilService {
	
	@Autowired
	private CommonMapper commonMapper;
	
	public List<MenuVO> getMenuInfo(String menuId) throws Exception{
		return commonMapper.getMenuInfo(menuId);
	}
	
	public String getMenuId(String screenName) throws Exception{
		return commonMapper.getMenuId(screenName);
	}
	
	public List<Map<String, Object>> getUrlList(String screenName) throws Exception{
		return commonMapper.getUrlList(screenName);
	}
	
	public List<Map<String, Object>> getUrlList() throws Exception{
		return commonMapper.getAllUrlList();
	}
	
	public List<MenuVO> getMenuList(String screenName) throws Exception{
		return commonMapper.getMenuList(screenName);
	}
}
