package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.AdminBannerServiceImpl;

@WebServlet("*.mc")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
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
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		
		System.out.println("uri : ==> " + uri);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath : ==> " + contextPath);
		
		String url = uri.substring(contextPath.length());
		
		String viewPage = "";
		AdminBannerServiceImpl bannerService = new AdminBannerServiceImpl();
		
		if (url.equals("/main.mc") || url.equals("/*.mc")) {
			System.out.println("<<< url ==>  /main.mc >>>");
			
			// 메인 - 관리자에서 등록한 배너이미지 조회
			bannerService.getMainBanner(request, response);
			
			viewPage = "common/main.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
