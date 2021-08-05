/**
 * 
 */
package com.javateam.project.mapper;

import org.apache.ibatis.annotations.Param;

import com.javateam.project.domain.Role;
import com.javateam.project.domain.Users;

/**
 * mapper
 * 
 * @author javateam
 *
 */
public interface UserMapper {
	
	Users getUserByUsername(String username);
	Role getUserRolesByUsername(String username);
	
	int hasUsername(String username);
	
	Users loadUserByUsername(String username);
	void insertUser(@Param("users") Users users);
	void insertUserRoles(@Param("username") String username, 
						 @Param("role") String role);
	
	// 추가 : 패쓰워드 갱신(수정)
	void updateUser(@Param("username") String username,
					@Param("password") String password);
	
	// 추가 : 회원 정보 삭제(패쓰워드, role)
	void deleteUser(String username);

}