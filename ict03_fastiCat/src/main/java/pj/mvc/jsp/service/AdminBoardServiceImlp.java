package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.AdminBoardDAO;
import pj.mvc.jsp.dao.AdminBoardDAOImpl;
import pj.mvc.jsp.dto.AdminBoardDTO;
import pj.mvc.jsp.page.Paging;

public class AdminBoardServiceImlp implements AdminBoardService{
	
	// 공연후기, 자유게시판 목록 조회
	@Override
	public void boardListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("service - boardListAction");
		
		String pageNum = request.getParameter("pageNum"); // null
		String category = request.getParameter("board_category");
		
		System.out.println("pageNum: " + pageNum);
		System.out.println("category: " + category);
		AdminBoardDAO dao = AdminBoardDAOImpl.getInstance();
		
		// 5-1 단계. 전체 게시글 갯수 카운트
		Paging paging = new Paging(pageNum);
		int total = dao.boardCnt(category);
		
		System.out.println("total: " + total);
		
		paging.setTotalCount(total);
		
		// 5-2 단계. 게시글 목록조회
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		
		List<AdminBoardDTO> list = dao.boardList(category, start, end);
		System.out.println("service list " + list);
		
		//6단계 jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
	}

	// 공연후기, 자유게시판 게시글 삭제
	@Override
	public void boardDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("service - boardDeleteAction");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		String category = request.getParameter("board_category");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		System.out.println("서비스 category : " + category + "board_num : " + board_num);
		
		AdminBoardDAO dao = AdminBoardDAOImpl.getInstance();
		int deleteCnt = dao.boardDelete(board_num, category);
		
		request.setAttribute("category", category);
		request.setAttribute("deleteCnt", deleteCnt);
	}

}
