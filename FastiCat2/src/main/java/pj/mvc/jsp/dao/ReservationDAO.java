package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.ShowDTO;


public interface ReservationDAO {
	
	// 선택 날짜에 있는 공연리스트			
	public List<ShowDTO> ResList(String curMonth);
	
	// 선택한 날짜에 있는 공연정보
	public ShowDTO ResDetailPageView(int showNum);

	// 선택한 날짜에 있는 공연정보
	public List<ShowDTO> ResDetailPageViewList(String showdto);

	// 선택한 날짜에 있는 공연정보
	public void showUpdate(int showNum,int quantity);
	
}
