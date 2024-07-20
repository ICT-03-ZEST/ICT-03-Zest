package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.NoticeDTO;


public interface AdminNoticeDAO {

	// 공지사항 등록
	public int noticeInsert(NoticeDTO dto);
	
	// 공지사항 갯수 구하기
	public int noticeCnt();
	
	// 공지사항 목록
	public List<NoticeDTO> noticeList(int start, int end);
	

	// 공지사항 상세 페이지
	public NoticeDTO getBoardDetail(int n_Board_Num);
	
	// 공지사항 수정  
	public int updateNotice(NoticeDTO dto);
	
	// 공지사항 삭제 
	public int deleteNotice(int n_Board_Num);
	
	
}
