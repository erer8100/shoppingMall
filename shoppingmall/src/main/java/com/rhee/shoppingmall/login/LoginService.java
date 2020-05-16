package com.rhee.shoppingmall.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private LoginMapper loginMapper;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetailVO detailVO=loginMapper.getLoginInfo(username);
		UserVO userVO=new UserVO();
		List<String> auths=new ArrayList<String>();
		
		userVO.setUserId(detailVO.getUserId());          //userVO.setId(detailVO.getId());
		userVO.setPassword(detailVO.getPassword());
		auths.add(detailVO.getUserRole());
		userVO.setAuthorities(auths);
		
		return userVO;
	}

	@Transactional
	public int signin(UserDetailVO vo) throws Exception{
		
		int result=0;
		
		if(loginMapper.countMember(vo.getUserId())>0)
		{
			return result;
		} 
		
		String rawPassword=vo.getPassword();
		
		String encodedPassword=passwordEncoder.encode(rawPassword);
		vo.setPassword(encodedPassword);
		vo.setUserRole("ROLE_USER");
		
		result=loginMapper.insertMemberInfo(vo);
		return result;
	}
	
	 public PasswordEncoder passwordEncoder() {
         return this.passwordEncoder;
    }
}
