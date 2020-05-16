package com.rhee.shoppingmall.util;

import java.util.List;

public class LeftMenuListVO {
	private int menuCode;
	private String menuUrl;
	private String urlPrefix;
	private List<LeftMenuVO> LeftMenuList;
	
	
	
	
	
	public int getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}
	public List<LeftMenuVO> getLeftMenuList() {
		return LeftMenuList;
	}
	public void setLeftMenuList(List<LeftMenuVO> leftMenuList) {
		LeftMenuList = leftMenuList;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getUrlPrefix() {
		return urlPrefix;
	}
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	public List<LeftMenuVO> getLefrMenuList() {
		return LeftMenuList;
	}
	
	@Override
	public String toString() {
		return "LeftMenuListVO [menuUrl=" + menuUrl + ", urlPrefix=" + urlPrefix + ", LeftMenuList=" + LeftMenuList
				+ "]";
	}
	
	
	
	
}
