package com.rhee.shoppingmall.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeftMenuConfig {
	
	String[] menuUrl= {"/purchase.do", "/admin.do"};
	String[] urlPrefix= {"/user", "/admin"};
	
	String[][] leftMenuNameList= {{"상품목록", "장바구니", "주문내역"}, {"상품등록", "관리자 지정", "배달 내역"}};
	String[][] leftMenuUrlList={{"/productList.do", "/cart.do", "/orderList.do"}, {"/productReg.do", "/assignAdmin.do", "/deliveryList.do"}};
	
	@Bean("purchaseLeft")
	public LeftMenuListVO purchaseLeftMenu() {
		
		
		LeftMenuListVO listVO=getLeftMenuList(0);
		
		
		return listVO;
	}
	
	@Bean("adminLeft")
	public LeftMenuListVO adminLeftMenu() {
		
		
		LeftMenuListVO listVO=getLeftMenuList(1);
		
		
		return listVO;
	}
	
	private LeftMenuListVO getLeftMenuList(int menuCode) {
		List<LeftMenuVO> leftMenuList=new ArrayList<LeftMenuVO>();
		LeftMenuListVO listVO=new LeftMenuListVO();
		
		listVO.setMenuCode(menuCode);
		listVO.setMenuUrl(menuUrl[menuCode]);
		listVO.setUrlPrefix(urlPrefix[menuCode]);
		
		for(int i=0; i<leftMenuNameList[menuCode].length; i++)
		{
			LeftMenuVO leftMenu=new LeftMenuVO();
			leftMenu.setMenuName(leftMenuNameList[menuCode][i]);
			leftMenu.setMenUrl("/user"+leftMenuUrlList[menuCode][i]);
			leftMenuList.add(leftMenu);
		}
		listVO.setLeftMenuList(leftMenuList);
		
		return listVO;
	}
}
