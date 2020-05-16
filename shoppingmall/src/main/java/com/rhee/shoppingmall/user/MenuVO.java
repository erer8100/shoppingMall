package com.rhee.shoppingmall.user;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;


public class MenuVO {
	
	private String menuId;
	private String menuName;
	private String menuField;
	private Object menuValue;	
	
	public MenuVO() {}
	
	public MenuVO(MenuVO vo) {
		super();
		this.menuId = vo.menuId;
		this.menuName = vo.menuName;
		this.menuField = vo.menuField;
		this.menuValue = vo.menuValue;
	}
	public Object getMenuValue() {
		return menuValue;
	}
	public void setMenuValue(Object menuValue) {
		this.menuValue=menuValue;
	}
	
	public void setMenuValue_ext(Object obj) {
		
		try {
			this.menuValue=new PropertyDescriptor(menuField, obj.getClass()).getReadMethod().invoke(obj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuField() {
		return menuField;
	}
	public void setMenuField(String menuField) {
		this.menuField = menuField;
	}
		public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	
	
	
}
