package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.CustomerDTO;
import pj.mvc.jsp.dto.ReservationDTO;

public interface CustomerDAO {

	// ID 중복확인 처리
	public int useridCheck(String strId);

	// 회원가입 처리
	public int insertCustomer(ReservationDTO dto);
	
	// 로그인 처리 / 회원정보 인증(수정, 탈퇴)
	public int idPasswordChk(String strId, String strPassword);
	
	// 회원정보 인증처리 및 탈퇴처리
	public int deleteCustomer(String strId);
	
	// 회원정보 인증처리 및 상세페이지
	public ReservationDTO getCustomerDetail(String strId);

	// 회원정보 수정 처리
	public int updateCustomer(ReservationDTO dto);
	
	
	// 관리자 - 회원목록 조회
	public List<CustomerDTO> memberList(int start, int end);
	
	// 관리자 - 회원 수
	public int memberCnt();
	
	// 관리자 - 회원 삭제
	public int deleteMember(String userid);

}
