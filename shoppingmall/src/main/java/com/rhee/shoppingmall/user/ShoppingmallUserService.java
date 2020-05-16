package com.rhee.shoppingmall.user;

import java.util.List;
import java.util.Map;

import com.rhee.shoppingmall.admin.ProductInfoVO;

public interface ShoppingmallUserService {
	List<ProductInfoVO> getProductInfoByKeyWord(String keyword) throws Exception;
	
	ProductInfoVO getProductInfoById(String productId) throws Exception;
	
	List<CartDetailVO> getCartDetailByCartId(String cartId) throws Exception;
	
	List<String> getCartIdByUserId(String userId) throws Exception;
	
	int updateCart(List<Map<String, Object>> params, CartVO cart) throws Exception;
	CartVO updateCart(List<Map<String, Object>> params) throws Exception;
	
	
}
