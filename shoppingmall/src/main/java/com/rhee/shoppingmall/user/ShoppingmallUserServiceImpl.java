package com.rhee.shoppingmall.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.rhee.shoppingmall.admin.ProductInfoVO;

@Service("shoppingmallUserService")
public class ShoppingmallUserServiceImpl implements ShoppingmallUserService {

	@Autowired
	private UserMapper usermapper;
	
	
	public List<ProductInfoVO> getProductInfoByKeyWord(String keyword) throws Exception {
		// TODO Auto-generated method stub
		
		return usermapper.getProductInfo(keyword);
	}
	
	
	@Override
	public ProductInfoVO getProductInfoById(String productId) throws Exception{
		return usermapper.getProductInfoById(productId);
	}

	
	@Override	
	public List<CartDetailVO> getCartDetailByCartId(String cartId) throws Exception{
		return usermapper.getCartDetailByCartId(cartId);
	}
	
	public List<String> getCartIdByUserId(String userId) throws Exception{
		return usermapper.getCartIdByUserId(userId);
	}
	
	
	public CartVO updateCart(List<Map<String, Object>> params) throws Exception{
		CartVO cart = new CartVO();
		
		LocalDate today = LocalDate.now();
		String todayString=today.format(DateTimeFormatter.BASIC_ISO_DATE);
		
		int max=usermapper.maxCartReg(todayString);
		String cartId=todayString+codifyInt(max+1, 4);
		
		cart.setId(cartId);
		cart.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		cart.setStatus("U");
		
		addCartDetailToCart(cart, params);
		
		/*
		 * cartDetail.setId("00"); cartDetail.setCartId(cartId);
		 * cartDetail.setProductId((String)params.get("productId")); ProductInfoVO
		 * productInfo=usermapper.getProductInfoById((String)params.get("productId"));
		 * 
		 * cartDetail.setName(productInfo.getName());
		 * cartDetail.setPrice(productInfo.getPrice());
		 * 
		 * try {
		 * cartDetail.setProductCount(Integer.parseInt((String)params.get("productCount"
		 * )));
		 * 
		 * } catch(Exception e) { e.printStackTrace(); }
		 * cartDetail.setAmount(cartDetail.getPrice()*cartDetail.getProductCount());
		 * cartDetailList.add(cartDetail);
		 * 
		 * cart.setCartDetailList(cartDetailList);
		 */
		
		
		return cart;
	}
	
	public int updateCart(List<Map<String, Object>> params, CartVO cart) throws Exception{
		
		cart.getCartDetailList().clear();
		
		addCartDetailToCart(cart, params);
		
		/*
		 * 
		 * int cartSize=cart.getCartDetailList().size(); cartSize++;
		 * cartDetail.setId(codifyInt(cartSize, 2)); cartDetail.setCartId(cart.getId());
		 * cartDetail.setProductId((String)params.get("productId")); ProductInfoVO
		 * productInfo=usermapper.getProductInfoById((String)params.get("productId"));
		 * 
		 * cartDetail.setName(productInfo.getName());
		 * cartDetail.setPrice(productInfo.getPrice()); try {
		 * cartDetail.setProductCount(Integer.parseInt((String)params.get("productCount"
		 * )));
		 * 
		 * } catch(Exception e) { e.printStackTrace(); }
		 * cartDetail.setAmount(cartDetail.getPrice()*cartDetail.getProductCount());
		 * 
		 * cart.getCartDetailList().add(cartDetail);
		 */
		
			
			/*
			 * cartMap.put("id",
			 * usermapper.maxCartDetailReg((String)params.get("cartId"))+1);
			 * cartMap.put("cartId", (String)params.get("cartId")); cartMap.put("productId",
			 * (String)params.get("productId")); cartMap.put("productCount",
			 * (String)params.get("productCount"));
			 * result+=usermapper.insertCartDetail(cartMap);
			 */
		
		
		return cart.getCartDetailList().size();
	}
	
	private void addCartDetailToCart(CartVO cart, List<Map<String, Object>> cartDetailInfoList) {
		
		
		int cartIndex=0;
		
		for(Map<String, Object> cartDetailInfo:cartDetailInfoList)
		{
			CartDetailVO cartDetail=new CartDetailVO();
			cartDetail.setId(codifyInt(cartIndex, 2));
			cartDetail.setCartId(cart.getId());
			cartDetail.setProductId((String)cartDetailInfo.get("productId"));
			
			ProductInfoVO productInfo=usermapper.getProductInfoById((String)cartDetailInfo.get("productId"));
			
			cartDetail.setName(productInfo.getName());
			cartDetail.setPrice(productInfo.getPrice());
			try {
				cartDetail.setProductCount(Integer.parseInt((String)cartDetailInfo.get("productCount")));
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			cartDetail.setAmount(cartDetail.getPrice()*cartDetail.getProductCount());
			List<CartDetailVO> cartDetailList=cart.getCartDetailList();
			if(!ObjectUtils.isEmpty(cartDetailList)) {
				cartDetailList.add(cartDetail);
			} else {
				cartDetailList=new ArrayList<CartDetailVO>();
				cartDetailList.add(cartDetail);
				cart.setCartDetailList(cartDetailList);
			}
			cartIndex++;
		}
	}
	
	private String codifyInt(int number, int digits) {
		
		String StrNumber=String.valueOf(number);
		int leng=StrNumber.length();
		
		String code="";
		if(leng>=0 && leng<=digits)
		{
			for(int i=0; i<digits-leng; i++) {
				code+="0";
			}
			code+=StrNumber;
		}
		
		System.out.println("-----------------------------------code "+code+"--------------------------------");
		return code;
	}
	
	
}
