package pj.mvc.jsp.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pj.mvc.jsp.dao.CustomerDAO;
import pj.mvc.jsp.dao.CustomerDAOImpl;
import pj.mvc.jsp.dao.ReservationDAO;
import pj.mvc.jsp.dao.ReservationDAOImpl;
import pj.mvc.jsp.dao.Show_ReservationDAO;
import pj.mvc.jsp.dao.Show_ReservationDAOImpl;
import pj.mvc.jsp.dto.ShowDTO;

public class ReservationServiceImpl implements ReservationService {

	@Override
	public void reservationListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DAO 객체 생성
		ReservationDAO dao = ReservationDAOImpl.getInstance();

		// 현재 날짜를 가져옴
		LocalDate currentDate = LocalDate.now(); // 현재 날짜 가져오기
		// 파라미터에서 curMonth를 가져옴
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
		String formattedMonth = currentDate.format(formatter);
		String curMonth = request.getParameter("curMonth");
		List<ShowDTO> list;
		if (curMonth == null) {
			System.out.println("curMonth : " + curMonth);
			// 선택된 월에 해당하는 공연 목록을 가져옴
			list = dao.ResList(formattedMonth);
		} else {
			System.out.println("curMonth : " + curMonth);
			// 선택된 월에 해당하는 공연 목록을 가져옴
			list = dao.ResList(curMonth);
		}

		// 결과를 request에 저장
		request.setAttribute("list", list);
	}

	@Override
	public void showTicketDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int showNum = Integer.parseInt(request.getParameter("showNum"));
		Date sendShowDay = Date.valueOf(request.getParameter("sendShowDay"));

		ReservationDAO dao = ReservationDAOImpl.getInstance();
		ShowDTO dto = dao.ResDetailPageView(showNum);
		request.setAttribute("dto", dto);
		request.setAttribute("sendShowDay", sendShowDay);
	}

	@Override
	public void showTicketDetailList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ShowDTO showdto = (ShowDTO) request.getAttribute("dto");
		ReservationDAO dao = ReservationDAOImpl.getInstance();
		List<ShowDTO> showList = dao.ResDetailPageViewList(showdto.getShowName());
		request.setAttribute("showList", showList);
	}

	@Override
	public void showTicketInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ReservationServiceImpl - showTicketInsert");

		HttpSession session = request.getSession();
		// 세션에서 사용자 ID 가져오기
		String userID = (String) session.getAttribute("sessionID");

		// 요청 파라미터로부터 값 가져오기
		int showNum = Integer.parseInt(request.getParameter("showNum"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		Date sendShowDay = Date.valueOf(request.getParameter("sendShowDay"));

		// DAO 인스턴스 생성
		ReservationDAO dao = ReservationDAOImpl.getInstance();
		Show_ReservationDAO show_dao = Show_ReservationDAOImpl.getInstance();
		CustomerDAO cdao = CustomerDAOImpl.getInstance();

		ShowDTO dto = new ShowDTO();
		dto.setShowNum(showNum);
		dto.setShowDay(sendShowDay);
		// 사용자 비밀번호 확인 (null 체크)
		if (cdao.getpassword(userID) != null) {
			// 공연 정보 업데이트
			dao.showUpdate(showNum, quantity);
			// 티켓 삽입
			show_dao.ticketInsert(dto, userID, totalPrice);

			System.out.println("티켓 삽입 완료: dto=" + dto + ", userID=" + userID + ", totalPrice=" + totalPrice);

		} else {
			System.out.println("유효하지 않은 사용자 또는 비밀번호가 null입니다.");
		}
		request.setAttribute("showNum", dto.getShowName());
		request.setAttribute("sendShowDay", dto.getShowDay());
		response.sendRedirect(
				request.getContextPath() + "/showTicket_Detail.do?showNum=" + showNum + "&sendShowDay=" + sendShowDay);
	}
}