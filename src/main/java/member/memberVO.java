package member;

import lombok.Data;

@Data
public class memberVO {
	private String mid; // 평문아이디
	private String mpassword1; // 평문암호
	private String mpassword2; // 암호화 암호
	private String mphone; // 전화번호 (관리자 수정가능)
	private String maddr1; //우편번호 (관리자 수정가능)
	private String maddr2; // 주소
	private String maddr3; // 상세주소
	private String mname; // 이름
	private int mage; // 나이
	private String mgender; // 성별
	private String mgrade; // 등급 (관리자 입력) VIP, 일반 손님, 관리자
	private String metc;// 특이사항 (관리자 입력)
}
