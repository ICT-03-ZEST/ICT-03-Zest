package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.CustomerServiceImpl;


@WebServlet("*.admember")
public class AdminMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AdminMemberController() {
        super();
    }

	
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
		CustomerServiceImpl service = new CustomerServiceImpl();
		
		// 회원목록 조회
		if(url.equals("/ad_member.admember")) {
			System.out.println("컨트롤러 - /ad_member.admember");
			
			viewPage = "admin/ad_member/ad_member.jsp";
			service.memberListAction(request, response);
		}
		
		// 회원 삭제
		else if(url.equals("/ad_memberDeleteAction.admember")) {
			System.out.println("컨트롤러 - /ad_memberDeleteAction.admember ");
			
			viewPage = "admin/ad_member/ad_memberDeleteAction.jsp";
			service.memberDeleteAction(request, response);
		}
		
		
		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher =request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
