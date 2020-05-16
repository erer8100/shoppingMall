package com.rhee.shoppingmall.admin;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/menu")
public class MenuController {

	@Resource(name="mainMenuService")
	MainMenuService mainMenuService;
	
	@RequestMapping(value="/mainMenu.do")
	@ResponseBody
	public List<MainMenuVO> mainMenu() throws Exception{
		
		List<MainMenuVO> mainMenuList=mainMenuService.getMainMenu();
		if(!ObjectUtils.isEmpty(mainMenuList))
		System.out.println(mainMenuList.toString());
		
		return mainMenuList;
	}
	
	@RequestMapping(value="/subMenu.do")
	@ResponseBody
	public List<MainMenuVO> subMenu(@RequestBody Map<String, Object> params) throws Exception{
		
		
		
		return mainMenuService.getSubMenu((String)params.get("menuUrl"));
	}
}
