package com.rhee.shoppingmall.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserVO implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4016956293729490070L;
	private String userId; 
	private String password; 
	private boolean isAccountNonExpired; 
	private boolean isAccountNonLocked; 
	private boolean isCredentialsNonExpired; 
	private boolean isEnabled; 
	private List <GrantedAuthority> authorities;

	public UserVO() {
		authorities=new ArrayList<GrantedAuthority>();
	}
	
	public void setAuthorities(List<String> auths) {
		for(String auth:auths)
		{
			authorities.add(new SimpleGrantedAuthority(auth));
		}
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
