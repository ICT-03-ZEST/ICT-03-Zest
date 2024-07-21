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

@WebServlet("*.do")
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public CustomerController() {
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
        String contextPath = request.getContextPath();
        String url = uri.substring(contextPath.length());

        String viewPage = "";
        ReservationService serviceCal = new ReservationServiceImpl();
        
        if(url.equals("/main.do") || url.equals("/*.do")) {
            serviceCal.reservationListAction(request, response);
            viewPage = "common/main.jsp";
            
        } else if(url.equals("/showTicket_Detail.do")) {
            serviceCal.showTicketDetail(request, response);
            serviceCal.showTicketDetailList(request, response);
            viewPage = "showTiket/showTiketDetail.jsp";
            
        } else if(url.equals("/showTicketInsert.do")) {
            serviceCal.showTicketInsert(request, response);
            return;
        }
        
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
    }
}