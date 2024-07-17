package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.AdminBoardServiceImlp;
import pj.mvc.jsp.util.ImageUploadHandler;

@WebServlet("*.adbc")
@MultipartConfig(location="D:\\dev\\workspace\\js_pj_fasticat\\src\\main\\webapp\\resources\\upload",
		fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class AdminBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMG_UPLOAD_DIR = "D:\\dev\\workspace\\js_pj_fasticat\\src\\main\\webapp\\resources\\upload";   
   
    public AdminBoardController() {
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
		
		ImageUploadHandler uploader = null; // 작성
		AdminBoardServiceImlp service = new AdminBoardServiceImlp();
		
		// 공연후기, 자유게시판 목록 조회
		if(url.equals("/board.adbc")) {
			System.out.println("컨트롤러 - /board.adbc");
			String category = request.getParameter("board_category");
			
			service.boardListAction(request, response);
			
			if(category.equals("공연후기")) {
				viewPage = "admin/ad_review/ad_reviewEdit.jsp";
			}
			else {
				viewPage = "admin/ad_freeboard/ad_freeboardEdit.jsp";
			}
			
		}
		
		// 공연후기, 자유게시판 게시물 삭제
		else if(url.equals("/boardDeleteAction.adbc")) {
			System.out.println("컨트롤러 - /boardDeleteAction.adbc");
			//String category = request.getParameter("board_category");
			
			service.boardDeleteAction(request, response);
			//viewPage = "admin/ad_review/ad_reviewDeleteAction.jsp";
			
			
			String category = request.getParameter("board_category");
			
			if(category.equals("공연후기")) {
				viewPage = "admin/ad_review/ad_reviewDeleteAction.jsp";
			}
			else {
				viewPage = "admin/ad_freeboard/ad_freeboardDeleteAction.jsp";
			}
			
		}
		
		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher =request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
