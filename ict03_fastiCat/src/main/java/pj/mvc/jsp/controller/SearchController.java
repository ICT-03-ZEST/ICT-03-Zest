package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.SearchServiceImpl;


@WebServlet("*.sc")
public class SearchController extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	       
	    public SearchController() {
	        super();
	    }

	    // 1단계. 웹브라우저가 전송한 HTTP 요청을 받음
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
	      // System.out.println("<<< action >>>");
	      
	      // 2. 클라이언트 요청 분석
	      
	      // 한글 안깨지게 처리
	      request.setCharacterEncoding("UTF-8");
	      
	      // http://localhost/jsp_pj_ict03/*.do
	      
	      String uri = request.getRequestURI();
	      // System.out.println("uri : ==> " + uri);  //   uri : ==> /jsp_pj_ict03/*.do
	      
	      String contextPath = request.getContextPath();
	      // System.out.println("contextPath : ==> " + contextPath);    // contextPath : ==> /jsp_pj_ict03
	      // System.out.println("contextPath 길이 : " + contextPath.length());  // 13
	      
	      String url = uri.substring(contextPath.length());  // uri.substring(시작위치, 끝);
	      System.out.println(url);
	      String viewPage = "";
	      SearchServiceImpl service = new SearchServiceImpl();
	      
	      // [ 검색창 메인 ]
	      if(url.equals("/search.sc")) {
	         System.out.println("<<< url ==>  /search.sc >>>");
	         service.boardListAction(request, response);
	         viewPage = "/customer/search/search.jsp";
	      }
	      
	      // 상세페이지
	       else if(url.equals("/search_detailAction.bc")) {
	           System.out.println("<<< url ==>  /search_detailAction.bc >>>");
	        
	           service.searchDetailAction(request, response);
	           viewPage = "/customer/search/search_detailAction.jsp";
	        }
	 	     
	      // [ 검색창 세부검색 ]
	      if(url.equals("/search_detailList.sc")) {
	         System.out.println("<<< url ==>  /search_detailList.sc >>>");
	         service.boardDetailListAction(request, response);
	         viewPage = "/customer/search/search_detailList.jsp";
	      }
	      // RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
	      RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	      dispatcher.forward(request, response);
	   }
}