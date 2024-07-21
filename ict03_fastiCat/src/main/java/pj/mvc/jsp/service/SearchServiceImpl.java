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

	// [ 게시판 목록 ]
    @Override
    public void boardListAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("service - boardListAction");
        
        // 3단계. 화면에서 입력받은 값을 가져오기
        String query = request.getParameter("query");
        String pageNum = request.getParameter("pageNum");
        
        System.out.println("query :" + query);
        // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
        SearchDAO dao = SearchDAOImpl.getInstance();
        
        // 5-1단계. 전체 게시글 갯수 카운트
        Paging paging = new Paging(pageNum);
        int total = dao.boardCnt(query);
        System.out.println("total => " + total);
        
        paging.setTotalCount(total);
        
        // 5-2단계. 게시글 목록 조회
        int start = paging.getStartRow();
        int end = paging.getEndRow();
        
        List<SearchDTO> list = dao.boardList(query, start, end); // 검색어 추가
        System.out.println("list : " + list);
        
        // 6단계. jsp로 처리결과 전달
        request.setAttribute("list", list);
        request.setAttribute("paging", paging);
        request.setAttribute("query", query); // 검색어도 전달
    }

	//공연 목록
	@Override
	public void concertListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - concertListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
        String query = request.getParameter("query");
		String pageNum = request.getParameter("pageNum");
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
        SearchDAO dao = SearchDAOImpl.getInstance();
		
		//5-1단계. 공연갯수
		int total = dao.getTotalCount(query);
		
		//5-2단계. 공연목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<SearchDTO> list = dao.concertList(query, start, end);
		//System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
        request.setAttribute("query", query); // 검색어도 전달
	}

    // [ 게시판 세부 목록 ]
    @Override
    public void boardDetailListAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("service - boardDetailListAction");
        
        // 3단계. 화면에서 입력받은 값을 가져오기
        String searchItem = request.getParameter("searchItem");
        String searchInput = request.getParameter("query");
        String pageNum = request.getParameter("pageNum");
        System.out.println("searchItem : " + searchItem);
        // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
        SearchDAO dao = SearchDAOImpl.getInstance();
        
        // 5-1단계. 전체 게시글 갯수 카운트
        Paging paging = new Paging(pageNum);
        int total = dao.boardCnt(searchInput);
        System.out.println("total => " + total);
        
        paging.setTotalCount(total);
        
        // 5-2단계. 게시글 목록 조회
        int start = paging.getStartRow();
        int end = paging.getEndRow();
        
        List<SearchDTO> list = dao.boardDetailList(searchItem, searchInput, start, end);
        System.out.println("list : " + list);
        
        // 6단계. jsp로 처리결과 전달
        request.setAttribute("list", list);
        request.setAttribute("paging", paging);
        if(searchItem.equals("writer")) {
        	searchItem = "공연종류";
        }
        else if(searchItem.equals("title")) {
        	searchItem = "이름";
        }
        else if(searchItem.equals("content")) {
        	searchItem = "장소";
        }
        request.setAttribute("searchItem", searchItem); // 검색 항목도 전달
        request.setAttribute("searchInput", searchInput); // 검색어도 전달
    }

	@Override
	public void searchDetailAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}