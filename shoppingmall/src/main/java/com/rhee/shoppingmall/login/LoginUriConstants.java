package com.rhee.shoppingmall.login;

import com.rhee.shoppingmall.UriConstants;

public class LoginUriConstants extends UriConstants{

	
	public static final String signin="signin";
	public static final String authenticate="authenticate";
	
	public static final String authenticateUri=slash+authenticate+htmlPrefix;
	public static final String signinUri=slash+signin+htmlPrefix;
	public static final String signinPath=slash+signin;
	public static final String signinJsp=signin+slash+signin;
	public static final String performSignin="performSignin";
	public static final String performSigninUri=slash+performSignin+htmlPrefix;
	public static final String login="login";
	public static final String loginUri=slash+login+htmlPrefix;
	public static final String end="end";
	public static final String endUri=slash+end+htmlPrefix;
}
