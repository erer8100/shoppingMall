package com.rhee.shoppingmall.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rhee.shoppingmall.user.MenuVO;
import com.rhee.shoppingmall.util.CommonUtilService;

@Controller
@RequestMapping(value=LoginUriConstants.signinPath)
public class SigninController {

	@Autowired
	private CommonUtilService commonService;
	
	
	@Autowired
	LoginService loginService;

	@RequestMapping(value=LoginUriConstants.signinUri)
	public String signinPage(Model model) throws Exception{
		
		String screenName="signin";
		
		model.addAttribute("urlList", commonService.getUrlList());
		List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
		model.addAttribute("menuInfoList", menuInfoList);
		return "manage/signin";
	}
	
	@RequestMapping(value=LoginUriConstants.performSigninUri)
	public String signin(UserDetailVO vo, Model model) throws Exception{
		
		if(loginService.signin(vo)>0)
		{
			String screenName="login";
			List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
			model.addAttribute("menuInfoList", menuInfoList);
			model.addAttribute("result", "success");
			return "manage/login";
		} else {
			model.addAttribute("result", "failure");
			String screenName="signin";
			List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
			model.addAttribute("menuInfoList", menuInfoList);
			return "manage/signin";
		}
		
		
	}
	
}
