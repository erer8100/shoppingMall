package com.rhee.shoppingmall.util;

public class LeftMenuVO {
	private String menuName;
	private String menUrl;
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenUrl() {
		return menUrl;
	}
	public void setMenUrl(String menUrl) {
		this.menUrl = menUrl;
	}
	@Override
	public String toString() {
		return "LeftMenuVO [menuName=" + menuName + ", menUrl=" + menUrl + "]";
	}
	
	
}
