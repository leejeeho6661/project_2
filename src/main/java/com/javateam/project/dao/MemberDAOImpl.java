package com.javateam.project.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.project.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertMember(MemberVO member) {

		log.debug("dao insertMember");
		sqlSession.insert("com.javateam.project.mapper.MemberMapper.insertMember",member);
		//sqlSession.getMapper(MemberMapper.class).insertMember(member);
	}

	@Override
	public boolean isMember(String id) {
		log.debug("dao isMember");
		return (int)sqlSession.selectOne("com.javateam.project.mapper.MemberMapper.isMember", id) == 1 ? true : false;
	}

	@Override
	public boolean isEnableEmail(String email) {
		log.debug("dao isEmail");
		return (int)sqlSession.selectOne("com.javateam.project.mapper.MemberMapper.isEnableEmail", email) == 1 ? true : false;
	}

	@Override
	public boolean isEnablePhone(String phone) {
		log.debug("dao isEnablePhone");
		return (int)sqlSession.selectOne("com.javateam.project.mapper.MemberMapper.isEnablePhone", phone) == 1 ? true : false;
	}

	@Override
	public MemberVO getMember(String Id) {
		
		log.debug("dao. getMember");
		log.info("============================"+sqlSession.selectOne("com.javateam.project.mapper.MemberMapper.getMember",Id).toString());
		return sqlSession.selectOne("com.javateam.project.mapper.MemberMapper.getMember",Id);
	}

	@Override
	public void updateMember(MemberVO member) {

		log.debug("dao. updateMember");
		sqlSession.update("com.javateam.project.mapper.MemberMapper.updateMember", member);
	}

	@Override
	public void deleteMember(String memberId) {

		log.debug("dao. deleteMember");
		sqlSession.delete("com.javateam.project.mapper.MemberMapper.deleteMember", memberId);
	}

	@Override
	public boolean isEnableEmailUpdate(String Id, String email) {

		log.debug("dao isEnableEmail");
		
		Map<String, String> map = new HashMap<>();
		map.put("Id", Id);
		map.put("email", email);
		
		return (int)sqlSession.selectOne("com.javateam.project.mapper.MemberMapper.isEnalbleEmailUpdate", map) == 1 ? true : false;
	}

	@Override
	public boolean isEnablePhoneUpdate(String Id, String memberPhone) {
		log.debug("dao isEnablePhoneUpdate");
		
		Map<String, String> map = new HashMap<>();
		log.info(Id+"=========="+memberPhone);
		map.put("Id", Id);
		map.put("phone", memberPhone);
		
		return (int)sqlSession.selectOne("com.javateam.project.mapper.MemberMapper.isEnablePhoneUpdate", map) == 1 ? true : false;
	}

	@Override
	public boolean isEnableNickname(String nickname) {
		log.debug("dao isEnableNickname");
		return (int)sqlSession.selectOne("com.javateam.project.mapper.MemberMapper.isEnableNickname", nickname) == 1 ? true : false;
	
	}

}
