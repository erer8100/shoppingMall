package com.rhee.shoppingmall.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.rhee.shoppingmall.admin.ProductInfoVO;

@Mapper
public interface UserMapper {
	List<ProductInfoVO> getProductInfo(String keyword);
	ProductInfoVO getProductInfoById(String productId);
	List<CartDetailVO> getCartDetailByCartId(String cartId);
	List<String> getCartIdByUserId(String userId);
	int insertCart(Map<String, Object> cartMap);
	int insertCartDetail(Map<String, Object> cartMap);
	int maxCartReg(String id);
	int maxCartDetailReg(String id);
	
}
