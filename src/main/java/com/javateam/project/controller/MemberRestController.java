package com.javateam.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.project.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("member")
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "idCheck.do", 
					produces = "text/plain; charset=UTF-8")
	public String isMember(@RequestParam("Id") String memberId) {
		
		log.debug("### MemberRestController : idCheck.do ###");
		return memberService.isMember(memberId.trim()) == true ? 
			   "아이디가 이미 존재합니다." : "사용할 수 있는 아이디입니다.";
	} //
	
	@RequestMapping(value = "nicknameCheck.do", 
					produces = "text/plain; charset=UTF-8")
		public String isEnableNickname(@RequestParam("Nickname") String nickname) {
		
		log.debug("### MemberRestController : idnickname.do ###");
		return memberService.isEnableNickname(nickname.trim()) == true ? 
			   "닉네임이 이미 존재합니다." : "사용할 수 있는 닉네임입니다.";
		} //
	
	@RequestMapping(value = "emailCheck.do", 
					produces = "text/plain; charset=UTF-8")
	public String isEnableEmail(@RequestParam("memberEmail") String memberEmail) {
	
		log.debug("### MemberRestController : emailCheck.do ###");
		return memberService.isEnableEmail(memberEmail.trim()) == true ? 
			   "중복 이메일이 이미 존재합니다." : "사용할 수 있는 이메일입니다.";
	} //
	
	@RequestMapping(value = "phoneCheck.do", 
					produces = "text/plain; charset=UTF-8")
	public String isEnablePhone(@RequestParam("memberPhone") String memberPhone) {
	
		log.debug("### MemberRestController : phoneCheck.do ###");
		log.debug(memberPhone);
		return memberService.isEnablePhone(memberPhone.trim()) == true ? 
			   "중복 연락처가 이미 존재합니다." : "사용할 수 있는 연락처입니다.";
	} //
	
	@RequestMapping(value = "emailCheckUpdate.do", 
				produces = "text/plain; charset=UTF-8")
	public String isEnableEmail(@RequestParam("Id") String Id,
			@RequestParam("memberEmail") String memberEmail	) {
	
	log.debug("### MemberRestController : emailCheckUpdate.do ###");
	return memberService.isEnableEmailUpdate(Id, memberEmail.trim()) == true ? 
		   "중복 이메일이 이미 존재합니다." : "사용할 수 있는 이메일입니다.";
	} //
	
	@RequestMapping(value = "phoneCheckUpdate.do", 
				produces = "text/plain; charset=UTF-8")
	public String phoneCheckUpdate(@RequestParam("Id") String Id,
					@RequestParam("memberPhone") String memberPhone) {
	
	log.debug("### MemberRestController : phoneCheckUpdate.do ###");
	log.info(Id+"=========="+memberPhone);
	return memberService.isEnablePhoneUpdate(Id, memberPhone.trim()) == true ? 
		   "중복 연락처가 이미 존재합니다." : "사용할 수 있는 연락처입니다.";
	} //

}