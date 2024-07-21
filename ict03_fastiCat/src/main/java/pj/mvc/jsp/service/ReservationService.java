package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReservationService {
	
	//달력날짜 예약 리스트
	public void reservationListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException; 
	
	//달력날짜 예약 : 상세페이지 리스트 정보 select
	public void showTicketDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException; 

	//달력날짜 예약 : 상세페이지 리스트 정보 select
	public void showTicketDetailList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException; 

	//달력날짜 예약 : 상세페이지 ticket 모달 구입페이지 insert
	public void showTicketInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException; 

}
