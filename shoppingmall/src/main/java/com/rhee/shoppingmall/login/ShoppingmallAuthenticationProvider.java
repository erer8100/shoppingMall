package com.rhee.shoppingmall.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ShoppingmallAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	LoginService loginService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserVO vo=null;
        List<GrantedAuthority> authorities=null;
        PasswordEncoder encoder=loginService.passwordEncoder();
        
        try {
        	vo= (UserVO)loginService.loadUserByUsername(username);
        	
        	 if (!encoder.matches(password, vo.getPassword()))
                 throw new BadCredentialsException("비밀번호 불일치");

        	 authorities = (List<GrantedAuthority>)vo.getAuthorities();
  	
        } catch(UsernameNotFoundException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        } catch(BadCredentialsException e) {
            e.printStackTrace();
            throw new BadCredentialsException(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return new UsernamePasswordAuthenticationToken(username, password, authorities);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
