package com.rhee.shoppingmall.admin;

public class MainMenuVO {
	private String menuId;
	private String parentMenuId;
	private String menuName;
	private String menuUrl;
	private int menuLevel;
	public int getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	private String menuAuth;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	public String getMenuAuth() {
		return menuAuth;
	}
	public void setMenuAuth(String menuAuth) {
		this.menuAuth = menuAuth;
	}
	@Override
	public String toString() {
		return "MainMenuVO [menuId=" + menuId + ", parentMenuId=" + parentMenuId + ", menuName=" + menuName
				+ ", menuUrl=" + menuUrl + ", menuLevel=" + menuLevel + ", menuAuth=" + menuAuth + "]";
	}
	
	
	
}
