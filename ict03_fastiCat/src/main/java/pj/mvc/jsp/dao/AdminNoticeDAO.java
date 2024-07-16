package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.AdminNoticeDTO;


public interface AdminNoticeDAO {

	// 공지사항 등록
	public int noticeInsert(AdminNoticeDTO dto);
	
	// 공지사항 갯수 구하기
	public int noticeCnt();
	
	// 공지사항 목록
	public List<AdminNoticeDTO> noticeList(int start, int end);
	

	// 공지사항 상세 페이지
	public AdminNoticeDTO getBoardDetail(int noticeNo);
	
	// 공지사항 수정  
	public int updateNotice(AdminNoticeDTO dto);
	
	// 공지사항 삭제 
	public int deleteNotice(int noticeNo);
	
	
}
