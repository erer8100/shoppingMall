package com.rhee.shoppingmall.admin;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainMenuService")
public class MainMenuServiceImpl implements MainMenuService {

	@Autowired 
	MainMenuMapper mainMenuMapper;
	
	@Override
	public List<MainMenuVO> getMainMenu() throws Exception {
		// TODO Auto-generated method stub
		return mainMenuMapper.getMainMenu();
	}

	@Override
	public List<MainMenuVO> getSubMenu(String menuUrl) throws Exception {
		// TODO Auto-generated method stub
		
		
		return mainMenuMapper.getSubMenu(menuUrl);
		
		
		/*
		 * if(mainMenuMapper.countMenu(menuUrl)>0) {
		 * 
		 * 
		 * Map<String, Object> paramMap=new HashMap<String, Object>();
		 * 
		 * paramMap.put("menuLevel", menuLevel); paramMap.put("menuUrl", menuUrl);
		 * 
		 * return mainMenuMapper.getSubMenu(); } else { return null; }
		 */
		
	}

}
