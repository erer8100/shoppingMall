package com.rhee.shoppingmall.user;

import java.util.ArrayList;
import java.util.List;

public class BaseMenuVO {
	private List<MenuVO> menuList;
	

	public List<MenuVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuVO> menuList) {
		
		List<MenuVO> menuList_new=new ArrayList<MenuVO>();
		for(MenuVO menu:menuList)
		{
			/*
			 * if(menu.getMenuField().equals("name")) { menu.setMenuValue(this.name); } else
			 * if(menu.getMenuField().equals("price")){ menu.setMenuValue(this.price); }
			 * else if(menu.getMenuField().equals("productCount")){
			 * menu.setMenuValue(this.productCount); } else
			 * if(menu.getMenuField().equals("amount")){ menu.setMenuValue(this.amount); }
			 */
			MenuVO menu_new=new MenuVO(menu);
			menu_new.setMenuValue_ext(this);
			menuList_new.add(menu_new);
		}
		this.menuList=menuList_new;
	}

}
