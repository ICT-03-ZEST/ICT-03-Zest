package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.NoticeService;
import pj.mvc.jsp.service.NoticeServiceImpl;


@WebServlet("*.nb")
public class NoticeBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeBoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		action(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//클라이언트 요청 분석
		request.setCharacterEncoding("UTF-8");
		
		String viewPage="";
		
		//service 호출
		NoticeService n_service = new NoticeServiceImpl();
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());
		
		//공지사항 목록
		if(url.equals("/notice_board.nb")) {
			System.out.println("url -> notice_board");
			
			n_service.NoticeBoardListAction(request, response);
			viewPage="/customer/normal_board/notice_board/notice_boardList.jsp";
		}
		
		//공지사항 상세페이지
		else if(url.equals("/notice_content.nb")) {
			System.out.println("url -> notice_content");
			
			n_service.NoticeBoardDetail_Action(request, response);
			viewPage="/customer/normal_board/notice_board/notice_content.jsp";
		}
		
		//공지사항 수정 
		else if(url.equals("/notice_updateAction.nb")) {
			System.out.println("url -> notice_updateAction.nb");
			
			n_service.NoticeBoard_UpdateAction(request, response);
			viewPage="/customer/normal_board/notice_board/notice_updateAction.jsp";
		}
		
		//공지사항 삭제 
		else if(url.equals("/notice_deleteAction.nb")) {
			System.out.println("url -> notice_deleteAction");
			
			n_service.NoticeBoard_DeleteAction(request, response);
			viewPage="/customer/normal_board/notice_board/notice_deleteAction.jsp";
		}
		
		//공지사항 작성
		else if(url.equals("/notice_insertAction.nb")) {
			System.out.println("url -> notice_insertAction");
			
			n_service.NoticeBoard_InsertAction(request, response);
			viewPage="/customer/normal_board/notice_board/notice_insertAction.jsp";
		}
		
		//RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
