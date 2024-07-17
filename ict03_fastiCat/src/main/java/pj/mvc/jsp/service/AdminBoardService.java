package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminBoardService {

	// 공연후기, 자유게시판 목록 조회
	public void boardListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 공연후기, 자유게시판 게시글 삭제
	public void boardDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException;
}
