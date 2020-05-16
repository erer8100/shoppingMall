package com.rhee.shoppingmall.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.rhee.shoppingmall.admin.ProductInfoVO;
import com.rhee.shoppingmall.admin.RegResult;
import com.rhee.shoppingmall.admin.ResultVO;
import com.rhee.shoppingmall.util.CommonUtilService;


@Controller
@RequestMapping(value="/user")
public class ShoppingmallUserController {
	
	String[] leftMenuList={"상품목록", "장바구니", "주문내역"};
	
	@Resource(name="shoppingmallUserService")
	private ShoppingmallUserService shoppingmallUserService;
	
	@Autowired
	private CommonUtilService commonService;
	
	
	
	
	@RequestMapping(value="/productList.do")
	public String productInfoPage(Model model) throws Exception{
		/*
		 * String screenName="productList";
		 * 
		 * model.addAttribute("urlList", commonService.getUrlList(screenName));
		 */
		
		return "div/productList";
	}
	
	@RequestMapping(value="/purchase.do")
	public String purchase(@RequestParam String includeUrl, Model model) throws Exception{
		
		if(StringUtils.isEmpty(includeUrl))
			model.addAttribute("includeUrl", "/user/productList.do");
		else
			model.addAttribute("includeUrl", includeUrl);
		
		model.addAttribute("menuId", "0000");
		model.addAttribute("urlList", commonService.getUrlList());
		return "contents/purchase";
	}
	
	
	
	@RequestMapping(value="/productDetail.do")
	public String productDetail(@RequestParam String productId, @RequestParam String cartMode, HttpSession session, Model model) throws Exception{
		
		
		List<MenuVO> menuInfoList=commonService.getMenuInfo(commonService.getMenuId("productDetail"));
		ProductInfoVO productInfo=shoppingmallUserService.getProductInfoById(productId);
		CartVO cart=((CartVO)session.getAttribute("cart"));
		
		if(!ObjectUtils.isEmpty(cart))
		{
			for(CartDetailVO detail:cart.getCartDetailList())
			{
				if(detail.getProductId().equals(productId))
				{
					model.addAttribute("productCount", detail.getProductCount());
					
				}
			}
		}
		
	
		for(MenuVO menu:menuInfoList)
		{
			menu.setMenuValue(BeanUtils.getProperty(productInfo, menu.getMenuField()));
		}
		model.addAttribute("menuInfoList", menuInfoList);
		model.addAttribute("cartMode", cartMode);
		model.addAttribute("productInfo", productInfo);
		return "div/productDetail";
	}
	
	@RequestMapping(value="/cart.do")
	public String cart(@RequestParam Map<String, Object> params, HttpSession session, Model model) throws Exception{
		
		String screenName="cart";
		
		
		
		List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
		//String userId=SecurityContextHolder.getContext().getAuthentication().getName();
		//List<String> cartIdList=shoppingmallUserService.getCartIdByUserId(userId);
		CartVO cart=new CartVO();
		cart=((CartVO)session.getAttribute("cart"));
		
		List<CartDetailVO> cartDetailList=new ArrayList<CartDetailVO>();
		int tot=0;
		if(!ObjectUtils.isEmpty(cart))
		{
			System.out.println(cart.toString());
			cartDetailList=cart.getCartDetailList();
			
			for(CartDetailVO cartDetail:cartDetailList)
			{
				tot+=cartDetail.getAmount();
				cartDetail.setMenuList(menuInfoList);
				
				
			}
			
		}
		
		String productId=(String)params.get("productId");
		model.addAttribute("productId", productId);
		model.addAttribute("menuInfoList", menuInfoList);
		
		/*
		 * for(String cartId:cartIdList) {
		 * cartDetailList.addAll(shoppingmallUserService.getCartDetailByCartId(cartId));
		 * }
		 */
		
		
		
		if(!ObjectUtils.isEmpty(productId))
		{
			/*
			 * int productCount=0; try {
			 * productCount=Integer.parseInt((String)params.get("productCount")); } catch
			 * (Exception e) { e.printStackTrace(); } //String
			 * duplicateDetailId=duplicateDetail(cartDetailList, productId, productCount);
			 */			
			if(!"Y".equals(params.get("isDuplicated")))
			{
				ProductInfoVO productInfo=shoppingmallUserService.getProductInfoById(productId);
				System.out.println(productInfo.toString());
				CartDetailVO vo=new CartDetailVO();
				
				vo.setProductId(productId);
				vo.setName(productInfo.getName());
				vo.setPrice(productInfo.getPrice());
				
				
				int amount=0;
				try {
					vo.setProductCount((Integer.parseInt((String)params.get("productCount"))));
					amount=Integer.parseInt((String)params.get("productCount"))*productInfo.getPrice();
					vo.setAmount(amount);
				} catch (Exception e) {
					e.printStackTrace();
				}
				vo.setMenuList(menuInfoList);
				cartDetailList.add(vo);
				tot+=amount;
			} else {
				
				for(CartDetailVO cartDetail:cartDetailList)
				{
					if(cartDetail.getProductId().equals(productId))
					{
						
						try {
							cartDetail.setProductCount(Integer.parseInt((String)params.get("productCount")));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			model.addAttribute("productId", productId);
		}
		model.addAttribute("cartMode", params.get("cartMode"));
		model.addAttribute("total", tot);
		//model.addAttribute("cartIdList", cartIdList);
		model.addAttribute("cartDetailList", cartDetailList);
		model.addAttribute("listSize", cartDetailList.size());
		session.setAttribute("cart", cart);
		return "div/cart";
	}
	
	/*
	 * private String duplicateDetail(List<CartDetailVO> cartList, String productId,
	 * int productCount) { if(!ObjectUtils.isEmpty(cartList)) { for(CartDetailVO
	 * detail:cartList) { if(productId.equals(detail.getProductId())) {
	 * detail.setProductCount(productCount); return detail.getId(); } } }
	 * 
	 * return null; }
	 */
	@RequestMapping(value="/getUrlList.do")
	@ResponseBody
	public List<Map<String, Object>> getUrlList() throws Exception{
		List<Map<String, Object>> list=commonService.getUrlList();
		
		return list;
	}
	@RequestMapping(value="/getproductList.do")
	@ResponseBody
	public List<ProductInfoVO> getProductInfoList(@RequestBody Map<String, Object> param) throws Exception{
		
		List<ProductInfoVO> productInfoList=shoppingmallUserService.getProductInfoByKeyWord((String)param.get("keyWord"));
		String screenName="productList";
		
		
		List<MenuVO> menuInfoList=commonService.getMenuList(screenName);
		for(ProductInfoVO ProductInfo:productInfoList)
		{
			ProductInfo.setMenuList(menuInfoList);
		}
		
		return productInfoList;
	}
	
	@RequestMapping(value="/updateCart.do")
	@ResponseBody
	public ResultVO updateCart(@RequestBody List<Map<String, Object>> params, HttpSession session, Principal principal) throws Exception{
		
		ResultVO result=new ResultVO();
		
		CartVO cart=(CartVO)session.getAttribute("cart");
		if(ObjectUtils.isEmpty(cart))
		{
			cart=shoppingmallUserService.updateCart(params);
			if(!ObjectUtils.isEmpty(cart)) {
				result.setResult(RegResult.SUCCESS);
			} else {
				result.setResult(RegResult.REGISTRATION_FAILURE);
			}
		} else {
			if(shoppingmallUserService.updateCart(params, cart)>0)
			{
				result.setResult(RegResult.SUCCESS);
			} else {
				result.setResult(RegResult.REGISTRATION_FAILURE);
			}
		}
		session.setAttribute("cart", cart);
		//cart.setUserId(principal.getName());
		
		
		return result;
		
	}
	
	@RequestMapping(value="/searchCart.do")
	@ResponseBody
	public Map<String, Object> searchCart(@RequestBody Map<String, Object> params, HttpSession session) throws Exception{
		
		
		Map<String, Object> result=new HashMap<String, Object>();
		
		result.put("result", "N");
		CartVO cart=(CartVO)session.getAttribute("cart");
		
		if(!ObjectUtils.isEmpty(cart))
		{
			for(CartDetailVO detail:cart.getCartDetailList())
			{
				
				if(detail.getProductId().equals(params.get("productId")))
				{
					
					result.put("result", "Y");
				}
			}
		} 
		
		return result;
		
	}
}
