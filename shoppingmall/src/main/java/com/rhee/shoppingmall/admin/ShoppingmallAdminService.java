package com.rhee.shoppingmall.admin;



import org.springframework.web.multipart.MultipartFile;

public interface ShoppingmallAdminService {
	int insertProductInfo(ProductInfoVO vo, String status) throws Exception;
	int deleteProduct(String productId) throws Exception;
	String saveUploadFiles(MultipartFile file, String uploadPath) throws Exception;
	ProductInfoVO getProductInfoById(String productId) throws Exception;
}
