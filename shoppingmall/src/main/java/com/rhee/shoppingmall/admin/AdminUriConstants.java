package com.rhee.shoppingmall.admin;

import com.rhee.shoppingmall.UriConstants;

public class AdminUriConstants extends UriConstants {
	
	public static final String admin="admin";
	public static final String adminPath=slash+admin;

	public static final String productReg="productReg";
	public static final String performReg="performReg";
	public static final String productRegUri=slash+productReg+htmlPrefix;
	public static final String performRegUri=slash+performReg+htmlPrefix;
	public static final String productRegJsp=contents+slash+productReg;
	
}
