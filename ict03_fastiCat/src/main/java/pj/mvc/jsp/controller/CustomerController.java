package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.ReservationService;
import pj.mvc.jsp.service.ReservationServiceImpl;
import pj.mvc.jsp.service.AdminBannerServiceImpl;
import pj.mvc.jsp.service.CustomerServiceImpl;

@WebServlet("*.do")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerController() {
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

	public void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("<<< action >>>");

		// 2. 클라이언트 요청 분석

		// 한글 안깨지게 처리
		request.setCharacterEncoding("UTF-8");

		// http://localhost/jsp_pj_ict03/*.do

		String uri = request.getRequestURI();
		System.out.println("uri : ==> " + uri); // uri : ==> /jsp_pj_ict03/*.do

		String contextPath = request.getContextPath();
		System.out.println("contextPath : ==> " + contextPath); // contextPath : ==>
		// /jsp_pj_ict03
		// System.out.println("contextPath 길이 : " + contextPath.length()); // 13

		String url = uri.substring(contextPath.length()); // uri.substring(시작위치, 끝);
		System.out.println(url);
		String viewPage = "";
		CustomerServiceImpl service = new CustomerServiceImpl();
		ReservationService serviceCal = new ReservationServiceImpl();
		AdminBannerServiceImpl bannerService = new AdminBannerServiceImpl();

		// 첫페이지
		if (url.equals("/main.do") || url.equals("/*.do")) {
			System.out.println("<<< url ==>  /main.do >>>");

			// 달력 공연날짜 기입
			serviceCal.reservationListAction(request, response);

			// 메인 - 관리자에서 등록한 배너이미지 조회
			bannerService.getMainBanner(request, response);

			viewPage = "common/main.jsp";
		}

		// ticket 구입 상세페이지
		else if (url.equals("/showTicket_Detail.do")) {
			System.out.println("<<< url ==>  /showTicket_Detail.do >>>");

			serviceCal.showTicketDetail(request, response);
            serviceCal.showTicketDetailList(request, response);
			viewPage = "showTiket/showTiketDetail.jsp";
		} 
		else if (url.equals("/showTicketInsert.do")) {
			serviceCal.showTicketInsert(request, response);
			return;
		}

		// [ 회원가입 ]
		else if (url.equals("/join.do")) {
			System.out.println("<<< url ==>  /join.do >>>");

			viewPage = "customer/join/join.jsp";
		}

		// [ 중복확인 ]
		else if (url.equals("/idConfirmAction.do")) {
			System.out.println("<<< url ==>  /idConfirmAction.do >>>");

			// 서비스 -> DAO(SELECT)
			service.idConfirmAction(request, response);
			viewPage = "customer/join/idConfirmAction.jsp";
		}

		// 회원가입 처리 페이지
		else if (url.equals("/joinAction.do")) {
			System.out.println("<<< url ==>  /joinAction.do >>>");

			// 서비스 -> DAO(INSERT)
			service.signInAction(request, response);
			viewPage = "customer/join/joinAction.jsp";
		}

		// [ login ]
		else if (url.equals("/login.do")) {
			System.out.println("<<< url ==>  /login.do >>>");

			viewPage = "customer/login/login.jsp";
		}

		else if (url.equals("/loginAction.do")) {
			System.out.println("<<< url ==>  /loginAction.do >>>");

			// 서비스 -> DAO(SELECT)
			service.loginAction(request, response);
			viewPage = "customer/login/loginAction.jsp";
		}

		else if (url.equals("/logout.do")) {
			System.out.println("<<< url ==>  /logout.do >>>");

			// 세션 삭제
			request.getSession().invalidate();
			viewPage = request.getContextPath() + "/main.do";
			response.sendRedirect(viewPage);
			return;
			// viewPage = "common/main.jsp";
		}

		// [ 회원수정 ]
		// 회원수정 - 인증화면
		else if (url.equals("/modifyCustomer.do")) {
			System.out.println("<<< url ==>  /modifyCustomer.do >>>");

			viewPage = "customer/mypage/customerInfo/modifyCustomer.jsp";
		}

		// 회원수정 상세페이지
		else if (url.equals("/modifyDetailAction.do")) {
			System.out.println("<<< url ==>  /modifyDetailAction.do >>>");

			service.modifyDetailAction(request, response);
			viewPage = "customer/mypage/customerInfo/modifyDetailAction.jsp";
		}

		// 회원수정 처리페이지
		else if (url.equals("/modifyCustomerAction.do")) {
			System.out.println("<<< url ==>  /modifyCustomerAction.do >>>");

			service.modifyCustomerAction(request, response);
			viewPage = "customer/mypage/customerInfo/modifyCustomerAction.jsp";
		}

		// [ 회원탈퇴 ]
		// 회원탈퇴 - 인증화면
		else if (url.equals("/deleteCustomer.do")) {
			System.out.println("<<< url ==>  /deleteCustomer.do >>>");

			viewPage = "customer/mypage/customerInfo/deleteCustomer.jsp";
		}

		// 회원탈퇴 처리
		else if (url.equals("/deleteCustomerAction.do")) {
			System.out.println("<<< url ==>  /deleteCustomerAction.do >>>");

			service.deleteCustomerAction(request, response);
			viewPage = "customer/mypage/customerInfo/deleteCustomerAction.jsp";
		}

		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
