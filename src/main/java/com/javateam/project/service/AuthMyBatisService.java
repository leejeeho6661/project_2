/**
 * 
 */
package com.javateam.project.service;

import com.javateam.project.domain.Users;

/**
 * @author javateam
 *
 */
public interface AuthMyBatisService {
	
	boolean hasUsername(String username);
	void insertUsers(Users users, String role);
	
	// 추가 : 패쓰워드 갱신(수정)
	void updateUsers(Users users);
	// 추가 : 회원 삭제
	void deleteUsers(String username);

} //