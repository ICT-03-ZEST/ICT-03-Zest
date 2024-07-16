package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.AdminFestivalDTO;

public interface AdminFestivalDAO {
	
	// 페스티벌 등록
	public int festivalInsert(AdminFestivalDTO dto);
	
	// 상품갯수
	public int festivalCnt();
	
	// 페스티벌 목록
	public List<AdminFestivalDTO> festivalList(int start, int end);
	
	// 페스티벌 상세페이지
	public AdminFestivalDTO festivalDetail(int fesNo);
	
	// 페스티벌 수정
	public int festivalUpdate(AdminFestivalDTO dto);
	
	// 페스티벌 삭제
	public int festivalDelete(int fesNo);
		
	
}
