package com.rhee.shoppingmall.admin;

import com.rhee.shoppingmall.user.BaseMenuVO;

public class ProductInfoVO extends BaseMenuVO{
	private String name;
	private String productId;
	private int price;
	private String productDesc;
	private String imageUrl;
	private String regDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "productInfoVO [name=" + name + ", productId=" + productId + ", price=" + price + ", productDesc="
				+ productDesc + ", imageUrl=" + imageUrl + ", regDate=" + regDate + "]";
	}
	
	
}
