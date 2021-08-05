package com.javateam.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.project.dao.MemberDAO;
import com.javateam.project.domain.MemberVO;
import com.javateam.project.domain.Users;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private AuthMyBatisService authMyBatisService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		
		log.debug("svc insertMember");
		dao.insertMember(member);
		
		// 패스워드 암호화
		BCryptPasswordEncoder passwordEncoder
			= new BCryptPasswordEncoder();
		String hashedPassword
			= passwordEncoder.encode(member.getPassword());
		
		Users users = new Users();
		users.setusername(member.getId());
		users.setPassword(hashedPassword);
		users.setEnabled(1);
		
		// 암호화 패스워드 및 롤 정보 저장
		authMyBatisService.insertUsers(users, "ROLE_USER");
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isMember(String id) {
		log.debug("svc inMember");
		return dao.isMember(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public boolean isEnableEmail(String email) {
		log.debug("svc isEnableEmail");
		return dao.isEnableEmail(email);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isEnablePhone(String phone) {

		log.debug("svc isEnablePhone");
		return dao.isEnablePhone(phone);
	}

	@Transactional(readOnly=true)
	@Override
	public MemberVO getMember(String memberId) {
	
		log.debug("svc getMember Id = "+memberId);
		return dao.getMember(memberId);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void updateMember(MemberVO member) {


		log.debug("svc updateMember");
		dao.updateMember(member);
		
		log.info("### 회원정보 :"+ member);
		log.info("### 패쓰워드 :"+ member.getPassword());
				
		// 패쓰워드 없으면(공백) 패쓰워드 변경 구문 통과(pass)
		if (!member.getPassword().trim().contentEquals("")) {
			
			log.info("############### 패쓰워드 변경 ############");
			// 패쓰워드 암호화
			log.info("--------------------------------------");
			BCryptPasswordEncoder passwordEncoder 
				= new BCryptPasswordEncoder();
			String hashedPassword 
				= passwordEncoder.encode(member.getPassword());
			
			Users users = new Users();
			users.setusername(member.getId());
			users.setPassword(hashedPassword);
			users.setEnabled(1);
			
			log.info("###### 신규 패쓰워드 : " + users.getPassword());
			
			// 암호화 패쓰워드 및 롤(Role) 정보 저장
			authMyBatisService.updateUsers(users);
		} else  {
			log.info("##### 패쓰워드 불변 ######");	
		} //		
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void deleteMember(String memberId) {

		log.debug("svc deleteMember");
		dao.deleteMember(memberId);
		
		authMyBatisService.deleteUsers(memberId);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isEnableEmailUpdate(String Id, String email) {
		log.debug("svc isEnableEmailUpdate(update)");
		return dao.isEnableEmailUpdate(Id, email);
	}

	@Override
	public boolean isEnablePhoneUpdate(String Id, String memberPhone) {
		log.debug("svc isEnablePhoneUpdate(update)");
		log.info(Id+"=========="+memberPhone);
		return dao.isEnablePhoneUpdate(Id, memberPhone);
	
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isEnableNickname(String nickname) {
		log.debug("svc isEnableNickname");
		return dao.isEnableNickname(nickname);
		
	}

}
