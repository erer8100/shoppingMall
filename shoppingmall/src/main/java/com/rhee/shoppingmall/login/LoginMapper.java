package com.rhee.shoppingmall.login;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	UserDetailVO getLoginInfo(String username);
	int insertMemberInfo(UserDetailVO vo);
	int updateMemberInfo(UserDetailVO vo);
	int countMember(String userId);
}
