package com.rhee.shoppingmall.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component("cart")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6840317669341440187L;

	private String id;
	private String userId;
	private String status;
	@Override
	public String toString() {
		return "CartVO [id=" + id + ", userId=" + userId + ", status=" + status + ", paidTime=" + paidTime
				+ ", cartDetailList=" + cartDetailList.toString() + "]";
	}
	private String paidTime;
	private List<CartDetailVO> cartDetailList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaidTime() {
		return paidTime;
	}
	public void setPaidTime(String paidTime) {
		this.paidTime = paidTime;
	}
	public List<CartDetailVO> getCartDetailList() {
		return cartDetailList;
	}
	public void setCartDetailList(List<CartDetailVO> cartDetailList) {
		this.cartDetailList = cartDetailList;
	}
	
}
