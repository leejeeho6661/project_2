package com.javateam.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.project.domain.MemberDTO;
import com.javateam.project.domain.MemberVO;
import com.javateam.project.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("Agree.do")
	public String agree() {
		log.info("agree");
		return "member/Agree";
	}
	@RequestMapping("SignUp.do")
	public String signUp() {
		log.info("signup");
		return "member/SignUp";
	}
	
	@RequestMapping("SignUp_write.do")
	public String write(MemberDTO memberDTO) {
		log.info("insert member");
		log.debug("회원정보 : " + memberDTO);
		MemberVO memberVO = new MemberVO(memberDTO);
		log.debug("저장 회원 정보 : " + memberVO);
		memberService.insertMember(memberVO);
		return "redirect:/";
	}
	
	
	// 회원정보 수정
	@RequestMapping("update.do")
	public String memberUpdateForm(@RequestParam("Id") String Id,
					Model model) {
	
		log.debug("회원수정폼");
		log.debug(Id);
		MemberVO memberVO = memberService.getMember(Id);
		
		log.info("MemberVO : "+memberVO);
		
		log.info("회원 정보 : VO -> DTO 변환");
		MemberDTO member = new MemberDTO(memberVO);		
		
		log.debug("MemberDTO : " + member);
		
		model.addAttribute("member", member);
		
		return "member/Member_Update";
	}
	
	@RequestMapping("updateProc.do")
	public String updateProc(@ModelAttribute("member") MemberDTO newMemberDTO,
							@RequestParam("Password") String newMemberPassword) {
		
		
		log.debug("회원수정 처리");
		
		log.info("#### 회원정보 : " + newMemberDTO);
			
		MemberDTO legacyMember = new MemberDTO(memberService.getMember(newMemberDTO.getId()));
		
		log.debug("##########################################");
		log.debug("기존 회원정보 : " + legacyMember);
		log.debug("수정 회원정보 : " + newMemberDTO);
		log.debug("신규 패쓰워드 : " + newMemberPassword);
				
		MemberVO memberVO = new MemberVO(newMemberDTO);
		
		// 패쓰워드 갱신 버그 패치
		String newMemberPw = newMemberPassword.trim().equals("") ? memberVO.getPassword()
						   : newMemberPassword;
		memberVO.setPassword(newMemberPw);
		
		log.debug("저장 회원 정보 : " + memberVO);		
		
		memberService.updateMember(memberVO);
		
		// 교정전
		// return "redirect:/member/member_update.do?memberId="+newMemberDTO.getMemberId();
		
		// 추가 : 이동 URL 조정
		// 교정후
		return "redirect:/";
	}
	
	@RequestMapping("deleteProc.do")
	public void memberDeleteProc(@RequestParam("Id") String Id,
					 HttpServletRequest request,
					 HttpServletResponse response) throws IOException {
	
		log.debug("회원삭제 처리 : " + memberService.isMember(Id));
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
		if (memberService.isMember(Id)==true) {
			Authentication auth 
	    	= SecurityContextHolder.getContext()
	    						   .getAuthentication();
			if (auth != null) {    
		        new SecurityContextLogoutHandler().logout(request, response, auth);
		        memberService.deleteMember(Id);
			}
		} else {
			
            out.println("<script>alert('회원정보가 존재하지 않습니다.'); history.go(-1);</script>");
            out.flush();

		}
		
		// 교정전	
		// return "redirect:/member/member_delete.do";
			
		// 추가 : 이동 URL 조정
		// 교정후
		out.println("<script>alert('삭제되었습니다.');location.href='/project';</script>");
        out.flush();
	}
	
}
