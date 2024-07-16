package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.ConcertDTO;

public interface ConcertDAO {

	// 공연등록
	public int concertInsert(ConcertDTO dto);
	
	// 상품갯수
	public int concertCnt();
	
	// 공연목록
	public List<ConcertDTO> concertList(int start, int end);
	
	// 공연 상세페이지
	public ConcertDTO concertDetail(int conNo);
	
	// 공연수정
	public int concertUpdate(ConcertDTO dto);
	
	// 공연삭제
	public int concertDelete(int conNo);
}
