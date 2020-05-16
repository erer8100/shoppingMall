package com.rhee.shoppingmall.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
	int insertProductInfo(ProductInfoVO vo);
	int updateProductInfo(ProductInfoVO vo);
	int deleteProduct(String productId);
	int maxProductReg(String prefix);
	ProductInfoVO getProductInfoById(String productId);
	
}
