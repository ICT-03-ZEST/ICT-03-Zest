package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.NoticeDTO;

public interface NoticeBoardDAO {
	
	//공지사항 목록
	public List<NoticeDTO> noticeBoardList(int start, int end);
	//공지사항 갯수 구하기
	public int NoticeBoardCnt();
	//조회수 증가 
	public void plusNoticeReadCnt(int num);
	//공지사항 상세페이지
	public NoticeDTO getNoticeBoardDetail(int num);
	//공지사항 수정처리
	public int updateNoticeBoard(NoticeDTO dto);
	//공지사항 삭제처리
	public int deleteNoticeBoard(int num);
	//공지사항 작성
	public int insertNoticeBoard(NoticeDTO dto);
	
}
