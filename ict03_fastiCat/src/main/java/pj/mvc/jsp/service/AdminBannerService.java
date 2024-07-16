package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminBannerService {
	// 배너등록
	public void bannerAddAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 배너목록
	public void bannerListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 배너 상세페이지
	public void bannerDetailAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 배너수정
	public void bannerUpdateAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 배너삭제
	public void bannerDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	

}
