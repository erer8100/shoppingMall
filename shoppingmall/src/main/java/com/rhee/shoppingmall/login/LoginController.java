package com.rhee.shoppingmall.login;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rhee.shoppingmall.user.MenuVO;
import com.rhee.shoppingmall.util.CommonUtilService;



@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private CommonUtilService commonService;
	
	@RequestMapping(value= LoginUriConstants.loginUri)
	public String login(Model model) throws Exception{
		String screenName="login";
		
		
		List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
		model.addAttribute("menuInfoList", menuInfoList);
		model.addAttribute("urlList", commonService.getUrlList());
		return "manage/login";
	}
	
	@RequestMapping(value=LoginUriConstants.endUri)
	public String end() throws Exception{
		
		return "manage/end";
	}
	
	
	
	
	
	
}
