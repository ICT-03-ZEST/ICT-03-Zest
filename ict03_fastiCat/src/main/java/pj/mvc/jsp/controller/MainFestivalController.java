package pj.mvc.jsp.controller;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.AdminFestivalServiceImpl;

@WebServlet("*.fv")
public class MainFestivalController extends HttpServlet {
private static final long serialVersionUID = 1L;

    public MainFestivalController() {
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
		
		AdminFestivalServiceImpl service = new AdminFestivalServiceImpl();
		
		// 페스티벌 목록 조회
		if(url.equals("/festivalList.fv")) {
			System.out.println("<<< url ==> /detailPage.fv >>>");
			
			service.mainFestivalList(request, response);
			viewPage = "customer/category_board/festival.jsp";
		
		}
		
		// 페스티벌 상세페이지 조회
		else if(url.equals("/festivInfo.fv")) {
			System.out.println("<<< url ==> /festivalDetail.fv >>>");
			
			service.festivalDetailAction(request, response);
			viewPage = "customer/category_board/festivInfo.jsp";
		
		}
		
		RequestDispatcher dispatcher =request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}	
}
