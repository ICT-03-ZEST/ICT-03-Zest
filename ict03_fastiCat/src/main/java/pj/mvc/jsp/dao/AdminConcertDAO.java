package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.AdminConcertDTO;

public interface AdminConcertDAO {

	// 공연등록
	public int concertInsert(AdminConcertDTO dto);
	
	// 상품갯수
	public int concertCnt();
	
	// 공연목록
	public List<AdminConcertDTO> concertList(int start, int end);
	
	// 공연 상세페이지
	public AdminConcertDTO concertDetail(int conNo);
	
	// 공연수정
	public int concertUpdate(AdminConcertDTO dto);
	
	// 공연삭제
	public int concertDelete(int conNo);
	
	// 메인 - 공연 목록 내림차순 정렬 
	public List<AdminConcertDTO> concertListMain(int start, int end);
}
