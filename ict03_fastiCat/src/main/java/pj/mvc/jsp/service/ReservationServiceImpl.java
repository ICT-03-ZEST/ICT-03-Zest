package pj.mvc.jsp.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.ReservationDAO;
import pj.mvc.jsp.dao.ReservationDAOImpl;
import pj.mvc.jsp.dto.ShowDTO;


public class ReservationServiceImpl implements ReservationService{

	public void reservationListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException{
		System.out.println("서비스 - reservationListAction");
		
		String curMonthS = request.getParameter("curMonth");
		
		System.out.println("curMonthS : " +curMonthS);
		ReservationDAO dao = ReservationDAOImpl.getInstance();
		
		List<ShowDTO> list = dao.ResList(curMonthS);
		System.out.println("list : " + list);
		request.setAttribute("list", list);
	}
	
	public void showTicketDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		System.out.println("서비스 - showTicketDetail");
		
		int showNum = Integer.parseInt(request.getParameter("showNum"));
		Date showDay = Date.valueOf(request.getParameter("showNum"));

		ReservationDAO dao = ReservationDAOImpl.getInstance();
		
		ShowDTO dto = dao.ResDetailPageView(showNum);
		System.out.println("dto : " + dto);
		System.out.println("showDay : " + showDay);
		request.setAttribute("dto", dto);
		
		
	}
	
}
	

