package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminNoticeService {
	
	// 공지사항 등록
	public void noticeAddAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 공지사항 목록
	public void noticeListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 공지사항 상세 페이지
	public void noticeDetailAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 공지사항 수정 
	public void noticeUpdateAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 공지사항 삭제
	public void noticeDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
}
