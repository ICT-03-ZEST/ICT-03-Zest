package pj.mvc.jsp.dao;

public interface Show_ReservationDAO {
	
	// 공연에 구입자가 쓴 비용 Insert
	public void ticketInsert(int showNum,String userID,int totalPrice);


}
