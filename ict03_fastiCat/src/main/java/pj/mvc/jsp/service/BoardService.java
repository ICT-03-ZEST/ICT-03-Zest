package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {

	// 게시판 목록 조회
	public void boardListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException; 
	
	// 게시글 상세페이지
	public void boardDetailAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	//좋아요 추가
	public void heartInsertAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException; 
	//좋아요 삭제 
	public void heartDeleteAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException; 
	
	//좋아요 조회 -  건수 있으면 채워진 하트
	public void boardHeartSelectAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException; 
	
	// 게시글 작성처리
	public void boardInsertAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	// 게시글 수정처리
	public void boardUpdateAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	// 게시글 삭제처리 - +checkbox 삭제 
	public void boardDeleteAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	// 댓글 작성처리
	public void commentAddAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	// 댓글 목록처리
	public void commentListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	// 댓글 수정처리
	public void commentModAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	// 댓글 삭제처리
	public void commentDelAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
