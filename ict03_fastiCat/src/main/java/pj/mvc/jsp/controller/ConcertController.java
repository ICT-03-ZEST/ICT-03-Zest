package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.ConcertServiceImpl;
import pj.mvc.jsp.util.ImageUploadHandler;

@WebServlet("*.con")
@MultipartConfig(location="D:\\dev\\workspace\\FastiCat_admin\\src\\main\\webapp\\resources\\upload",
		fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class ConcertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMG_UPLOAD_DIR = "D:\\dev\\workspace\\FastiCat_admin\\src\\main\\webapp\\resources\\upload";
       
    public ConcertController() {
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
		ConcertServiceImpl service = new ConcertServiceImpl();
		
		// 공연등록 화면
		if(url.equals("/ad_concertAdd.con")) {
			System.out.println("<<< url ==> /ad_concertAdd.con >>>");
			
			viewPage = "admin/ad_concert/ad_concertAdd.jsp";
		
		}
		
		// 공연등록 처리
		else if(url.equals("/ad_concertAddAction.con")) {
			System.out.println("<<< url ==> /ad_concertAddAction.con >>>");
			
			// 추가 : 서비스 호출 전에 추가!
			String contentType = request.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR); // img 경로(upload 폴더)
				uploader.imageUpload(request, response);
			}
			
			// 서비스 호출
			service.concertAddAction(request, response);
			
			viewPage = "admin/ad_concert/ad_concertAddAction.jsp";
		}
		
		
		// 공연목록 조회
		else if(url.equals("/ad_concertEdit.con")) {
			System.out.println("<<< url ==> /ad_concertEdit.con >>>");
			
			viewPage = "admin/ad_concert/ad_concertEdit.jsp";
			service.concertListAction(request, response);
		
		}
		
		// 공연 수정 상세페이지 조회
		else if(url.equals("/ad_concertModify.con")) {
			System.out.println("<<< url ==> /ad_concertModify.con >>>");
			
			viewPage = "admin/ad_concert/ad_concertModify.jsp";
			service.concertDetailAction(request, response);
		
		}
		
		
		// 공연 수정
		else if(url.equals("/ad_concertModifyAction.con")) {
			System.out.println("<<< url ==> /ad_concertModifyAction.con >>>");
			
			// 추가 : 서비스 호출 전에 추가!
			String contentType = request.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR); // img 경로(upload 폴더)
				uploader.imageUpload(request, response);
			}
			
			// 서비스 호출
			service.concertUpdateAction(request, response);
			viewPage = "admin/ad_concert/ad_concertModifyAction.jsp";
		}
		
		
		// 공연 삭제
		else if(url.equals("/ad_concertDeleteAction.con")) {
			System.out.println("<<< url ==> /ad_concertDeleteAction.con >>>");
			
			viewPage = "admin/ad_concert/ad_concertDeleteAction.jsp";
			service.concertDeleteAction(request, response);
		
		}
		
		
		
		
		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher =request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}	
}
