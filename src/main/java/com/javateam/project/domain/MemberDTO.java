package com.javateam.project.domain;

import java.sql.Date;
import java.util.Arrays;

public class MemberDTO {

	
	private String Id;
	private String Password;
	private String RePassword;
	private String Name;
	private String birthdayY;
	private String birthdayM;
	private String birthdayD;
	private String Sex;
	private String memNickname;
	
	private String mobile1;
	private String mobile2;
	private String mobile3;
	
	private String memEmail;
	private String emailDnsList;
	
	private String memberzip;
	private String memberAddressBasic;
	private String detailAddress;
	
	private String[] categoryCode;
	private Date memberJoinDate;


	public Date getMemberJoinDate() {
		return memberJoinDate;
	}

	public void setMemberJoinDate(Date memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRePassword() {
		return RePassword;
	}

	public void setRePassword(String rePassword) {
		RePassword = rePassword;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBirthdayY() {
		return birthdayY;
	}

	public void setBirthdayY(String birthdayY) {
		this.birthdayY = birthdayY;
	}

	public String getBirthdayM() {
		return birthdayM;
	}

	public void setBirthdayM(String birthdayM) {
		this.birthdayM = birthdayM;
	}

	public String getBirthdayD() {
		return birthdayD;
	}

	public void setBirthdayD(String birthdayD) {
		this.birthdayD = birthdayD;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getMemNickname() {
		return memNickname;
	}

	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getMobile3() {
		return mobile3;
	}

	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getEmailDnsList() {
		return emailDnsList;
	}

	public void setEmailDnsList(String emailDnsList) {
		this.emailDnsList = emailDnsList;
	}

	public String getMemberzip() {
		return memberzip;
	}

	public void setMemberzip(String memberzip) {
		this.memberzip = memberzip;
	}

	public String getmemberAddressBasic() {
		return memberAddressBasic;
	}

	public void setmemberAddressBasic(String memberAddressBasic) {
		this.memberAddressBasic = memberAddressBasic;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String[] getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String[] categoryCode) {
		this.categoryCode = categoryCode;
	}



	public MemberDTO(String id, String password, String rePassword, String name, String birthdayY, String birthdayM,
			String birthdayD, String sex, String memNickname, String mobile1, String mobile2, String mobile3,
			String memEmail, String emailDnsList, String memberzip, String memberAddressBasic, String detailAddress,
			String[] categoryCode, Date memberJoinDate) {
		super();
		Id = id;
		Password = password;
		RePassword = rePassword;
		Name = name;
		this.birthdayY = birthdayY;
		this.birthdayM = birthdayM;
		this.birthdayD = birthdayD;
		Sex = sex;
		this.memNickname = memNickname;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.mobile3 = mobile3;
		this.memEmail = memEmail;
		this.emailDnsList = emailDnsList;
		this.memberzip = memberzip;
		this.memberAddressBasic = memberAddressBasic;
		this.detailAddress = detailAddress;
		this.categoryCode = categoryCode;
		this.memberJoinDate = memberJoinDate;
	}



	@Override
	public String toString() {
		return "memberDTO [Id=" + Id + ", Password=" + Password + ", RePassword=" + RePassword + ", Name=" + Name
				+ ", birthdayY=" + birthdayY + ", birthdayM=" + birthdayM + ", birthdayD=" + birthdayD + ", Sex=" + Sex
				+ ", memNickname=" + memNickname + ", mobile1=" + mobile1 + ", mobile2=" + mobile2 + ", mobile3="
				+ mobile3 + ", memEmail=" + memEmail + ", emailDnsList=" + emailDnsList + ", memberzip=" + memberzip
				+ ", memberAddressBasic=" + memberAddressBasic + ", detailAddress=" + detailAddress + ", categoryCode="
				+ Arrays.toString(categoryCode) + ", memberJoinDate=" + memberJoinDate + "]";
	}

	public MemberDTO() {}

	public MemberDTO(MemberVO memberVO) {
		this.Id = memberVO.getId();
		this.Name= memberVO.getName();
		String birth[] = memberVO.getBirth().split("/");
		this.birthdayY = birth[0];
		this.birthdayM = birth[1];
		this.birthdayD = birth[2];
		this.Sex = memberVO.getSex();
		this.memNickname = memberVO.getNickname();
		this.mobile1 = memberVO.getPhone().split("-")[0];
		this.mobile2 = memberVO.getPhone().split("-")[1];
		this.mobile3 = memberVO.getPhone().split("-")[2];
		this.memEmail = memberVO.getEmail().split("@")[0];
		this.emailDnsList = memberVO.getEmail().split("@")[1];
		this.memberzip = memberVO.getAddress_Zip();
		if(memberVO.getAddress().equals("/") || memberVO.getAddress() ==null) {
			this.memberAddressBasic = "";
			this.detailAddress = "";
		}
		else {
			this.memberAddressBasic = memberVO.getAddress().split("/")[0];
			this.detailAddress = memberVO.getAddress().split("/")[1];
		}
		if(memberVO.getInterest() !=null) {
			String[] intereset = memberVO.getInterest().trim().split(" ");
			this.categoryCode = intereset;
		}
		this.memberJoinDate = memberVO.getJoinDate();
	}
	
	
	
	
}
