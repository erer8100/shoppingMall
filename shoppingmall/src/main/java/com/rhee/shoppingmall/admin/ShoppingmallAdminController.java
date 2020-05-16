package com.rhee.shoppingmall.admin;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rhee.shoppingmall.user.MenuVO;
import com.rhee.shoppingmall.util.CommonUtilService;




@Controller
@RequestMapping(AdminUriConstants.adminPath)
public class ShoppingmallAdminController {
	
	
	@Resource(name="shoppingmallAdminService")
	private ShoppingmallAdminService adminService;
	
	private String imageLocalPath="C:\\spring\\workspace\\shoppingmall\\src\\main\\webapp\\resources\\image\\";
	private String imageUrlPath="/resources/image/";
	
	@Autowired
	private CommonUtilService commonService;
	
	@RequestMapping(value= "/productEdit.do")
	public String productEdit(@RequestParam String productId, Model model) throws Exception{
		
		ProductInfoVO vo=adminService.getProductInfoById(productId);
		String screenName="productReg";
		
		List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
		vo.setMenuList(menuInfoList);
		
		if(!ObjectUtils.isEmpty(vo))
		{
			/*
			 * model.addAttribute("status", "update"); model.addAttribute("name",
			 * vo.getName()); model.addAttribute("productId", vo.getProductId());
			 * model.addAttribute("price", vo.getPrice()); model.addAttribute("productDesc",
			 * vo.getProductDesc()); 
			 */
			model.addAttribute("imageUrl", vo.getImageUrl());
			model.addAttribute("menuInfoList", vo.getMenuList());
		} else {
			model.addAttribute("status", "fail");
		}
		
		return "div/productReg";
	}
	
	@RequestMapping(value= "/admin.do")
	public String admin(@RequestParam String includeUrl, Model model) throws Exception{
		
		String screenName="productReg";
		
		List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
		model.addAttribute("status", "init");
		model.addAttribute("menuInfoList", menuInfoList);
		if(StringUtils.isEmpty(includeUrl))
			model.addAttribute("includeUrl", "/admin/productReg.do");
		else
			model.addAttribute("includeUrl", includeUrl);
		model.addAttribute("menuId", "0004");
		model.addAttribute("urlList", commonService.getUrlList());
		
		return "contents/admin";
	}
	
	@RequestMapping(value= "/deleteProduct.do")
	@ResponseBody
	public ResultVO deleteProduct(@RequestBody Map<String, Object> param, Model model) throws Exception{
		
		ResultVO result=new ResultVO();
		
		if(adminService.deleteProduct((String)param.get("productId"))>0)
		{
			result.setResult(RegResult.SUCCESS);
		} else {
			result.setResult(RegResult.REGISTRATION_FAILURE);
		}
		
		return result;
	}
	
	@RequestMapping(value= AdminUriConstants.productRegUri)
	public String productReg(Model model) throws Exception{
		
		String screenName="productReg";
		
		List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
		model.addAttribute("status", "init");
		model.addAttribute("menuInfoList", menuInfoList);
		
		return "div/productReg";
	}
	
	@RequestMapping(value= AdminUriConstants.performRegUri)
	@ResponseBody
	public ResultVO performReg(@RequestBody Map<String, Object> params) throws Exception{
		
		ProductInfoVO vo=new ProductInfoVO();
		ResultVO result=new ResultVO();
		
		vo.setProductId((String)params.get("productId"));
		vo.setName((String)params.get("productName"));
		vo.setPrice(Integer.parseInt((String)params.get("price")));
		vo.setProductDesc((String)params.get("productDesc"));
		vo.setImageUrl((String)params.get("imageFileUrl"));
		
		
		if(adminService.insertProductInfo(vo, (String)params.get("status"))>0)
		{
			result.setResult(RegResult.SUCCESS);
			
		}  else {
			result.setResult(RegResult.REGISTRATION_FAILURE);
		}
		
		return result;
		
	}
	
	
	
	@RequestMapping(value= "/fileUploadDiv.do")
	public String fileUploadDiv(@RequestBody Map<String, Object> param, Model model) throws Exception {
		
		model.addAttribute("imageUrl", param.get("imageUrl"));
		
		return "div/fileUploadDiv";
	}
	
	@RequestMapping(value= "/fileUpload.do")
	@ResponseBody
	public FileUploadResultVO fileUpload(@RequestPart("upload") MultipartFile file) throws Exception {
		
		String filePath=adminService.saveUploadFiles(file, imageLocalPath);
		FileUploadResultVO result=new FileUploadResultVO();
		
		if(filePath!=null)
		{
			result.setResult(RegResult.SUCCESS);
			result.setFileUrl(imageUrlPath+filePath.substring(filePath.lastIndexOf("\\")+1));
		} else {
			result.setResult(RegResult.UPLOAD_FAILURE);
		}
		
		return result;
	}
	
}
