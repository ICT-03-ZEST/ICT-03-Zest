package pj.mvc.jsp.controller;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.AdminConcertServiceImpl;

@WebServlet("*.cc")
public class MainConcertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainConcertController() {
        super();
    }

    // 1단계. 웹브라우저가 전송한 HTTP요청을 받음
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		action(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void action(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		// 2. 클라이언트 요청 분석
		
		// 한글 안깨지게 처리
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = "";
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());	// uri.substring(시작위치, 끝);
		
		AdminConcertServiceImpl service = new AdminConcertServiceImpl();
		
		// 공연목록 조회
		if(url.equals("/concertList.cc")) {
			System.out.println("<<< url ==> /concert.cc>>>");
			
			service.mainConcertList(request, response);
			viewPage = "customer/category_board/concert.jsp";
		}
		
		// 공연 수정 상세페이지 조회
		else if(url.equals("/concertInfo.cc")) {
			System.out.println("<<< url ==> /concertInfo.cc >>>");
			
			service.concertDetailAction(request, response);
			viewPage = "customer/category_board/concertInfo.jsp";
		}
		
		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}	
}
