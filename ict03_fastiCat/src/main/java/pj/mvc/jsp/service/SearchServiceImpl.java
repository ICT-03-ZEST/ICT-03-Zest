package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.SearchDAO;
import pj.mvc.jsp.dao.SearchDAOImpl;
import pj.mvc.jsp.dto.SearchDTO;
import pj.mvc.jsp.page.Paging;

public class SearchServiceImpl implements SearchService{

	@Override
	public void boardListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("service - boardListAction");
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		SearchDAO dao = SearchDAOImpl.getInstance();
	    
		// 5-1단계. 전체 게시글 갯수 카운트
	    Paging paging = new Paging(pageNum);
	    int total = dao.boardCnt();
	    System.out.println("total => " + total);
	    
	    paging.setTotalCount(total);
	    
		// 5-2단계. 게시글 목록 조회
	    int start = paging.getStartRow();
	    int end = paging.getEndRow();
	    
		List<SearchDTO> list = dao.boardList(start, end);
		System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
	
	}

	@Override
	public void searchDetailAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 3단계. 화면에서 입력받은 값을 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		SearchDAO dao = SearchDAOImpl.getInstance();
	    
		// 5-1단계. 게시글 상세 페이지
		SearchDTO dto = dao.getBoardDetail(num);
	    
		// 5-2단계. 조회수 증가
	    dao.plusReadCnt(num);
		
		// 6단계. jsp로 처리결과 전달
	    request.setAttribute("dto", dto);
	}
}
