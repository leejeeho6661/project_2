package com.javateam.project.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVO {

    /** 아이디 */
    private String Id;
   
    /** 패스워드 */
    private String Password;
    
    /** 별명 */
    private String Nickname;
    
    /** 이름 */
    private String Name;
    
    /** 성별 */
    private String sex;
    
    /** 이메일 */
    private String Email;
    
    /** 연락처 */
    private String Phone;
    
    /** 생년월일 */
    private String Birth;
    
    /** 우편번호 */
    private String Address_Zip;
    
    /** 기본주소 */
    private String Address;
    
    /** 관심 분야 */
    private String interest;
    
    /** 가입일 */
    private Date JoinDate;

	public MemberVO() {
	}
    
    /**
     * DTO -> VO
     * 
     */
    
	public MemberVO(MemberDTO memberDTO) {
		this.Id = memberDTO.getId();
		this.Password = memberDTO.getPassword();
		this.Nickname = memberDTO.getMemNickname();
		this.Name = memberDTO.getName();
		this.sex = memberDTO.getSex();
		this.Email = memberDTO.getMemEmail()+"@"+memberDTO.getEmailDnsList();
		this.Phone = memberDTO.getMobile1()+"-"+memberDTO.getMobile2()+"-"+memberDTO.getMobile3();
		this.Birth = memberDTO.getBirthdayY()+"/"+memberDTO.getBirthdayM()+"/"+memberDTO.getBirthdayD();
		this.Address_Zip = memberDTO.getMemberzip();
		if(memberDTO.getmemberAddressBasic() !=null || memberDTO.getDetailAddress() != null)
			this.Address = memberDTO.getmemberAddressBasic() +"/"+ memberDTO.getDetailAddress();
		else {
			this.Address="";
		}
		if(memberDTO.getCategoryCode()!=null) {
			for(int i=0;i<memberDTO.getCategoryCode().length;i++) {
				if(i==0)
					this.interest = memberDTO.getCategoryCode()[i]+" ";
				else if(memberDTO.getCategoryCode()[i] != null)
					this.interest += memberDTO.getCategoryCode()[i]+" ";
			}
		}
		else
			this.interest = "";
		this.JoinDate = memberDTO.getMemberJoinDate();
		
	}
	
}
