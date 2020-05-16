package com.rhee.shoppingmall.tiles;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rhee.shoppingmall.util.CommonUtilService;

@Controller
public class MainController {
	
	
	@Autowired
	private CommonUtilService commonService;
	
	@RequestMapping(value="/main/getUrlList.do")
	@ResponseBody
	public List<Map<String, Object>> getUrlList() throws Exception{
		List<Map<String, Object>> list=commonService.getUrlList();
		
		return list;
	}
	
	@RequestMapping(value="/index.do")
	public String home(Model model) throws Exception{
		
		
		return MainUriConstants.homeJsp;
	}
}
