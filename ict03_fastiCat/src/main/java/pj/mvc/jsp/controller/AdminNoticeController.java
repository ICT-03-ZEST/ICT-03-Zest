package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.AdminNoticeServiceImpl;

@WebServlet("*.not")
public class AdminNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminNoticeController() {
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
		
		AdminNoticeServiceImpl service = new AdminNoticeServiceImpl();
		
		// 공지사항 등록화면
		if(url.equals("/ad_noticeAdd.not")) {
			System.out.println("<<< url ==> /ad_noticeAdd.not >>>");
			
			viewPage = "admin/ad_notice/ad_noticeAdd.jsp";
		
		}
		
		// 공지사항등록 처리
		else if(url.equals("/ad_noticeAddAction.not")) {
			System.out.println("<<< url ==> /ad_noticeAddAction.not >>>");
			
			
			// 서비스 호출
			service.noticeAddAction(request, response);
			
			viewPage = "admin/ad_notice/ad_noticeAddAction.jsp";
		}
		
		// 공지사항목록 조회
		else if(url.equals("/ad_noticeEdit.not")) {
			System.out.println("<<< url ==> /ad_noticeEdit.not >>>");
			
			viewPage = "admin/ad_notice/ad_noticeEdit.jsp";
			service.noticeListAction(request, response);
		
		}
		
		// 공지사항 수정 상세페이지 조회
		else if(url.equals("/ad_noticeModify.not")) {
			System.out.println("<<< url ==> /ad_noticeModify.not >>>");
			
			viewPage = "admin/ad_notice/ad_noticeModify.jsp";
			service.noticeDetailAction(request, response);
		
		}
		
		
		// 공연 수정
		else if(url.equals("/ad_noticeModifyAction.not")) {
			System.out.println("<<< url ==> /ad_concertModifyAction.not >>>");
			
			
			// 서비스 호출
			service.noticeUpdateAction(request, response);
			viewPage = "admin/ad_notice/ad_noticeModifyAction.jsp";
		}
		
		
		// 공연 삭제
		else if(url.equals("/ad_noticeDeleteAction.not")) {
			System.out.println("<<< url ==> /ad_concertDeleteAction.not >>>");
			
			viewPage = "admin/ad_notice/ad_noticeDeleteAction.jsp";
			service.noticeDeleteAction(request, response);
		
		}
		
		
		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher =request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	
	}
}
