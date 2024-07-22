package pj.mvc.jsp.dto;

import java.sql.Date;

public class MyReservationDTO {

	private int showId;
	private String showName;
	private String userName;
	private int totalPrice;
	private Date showDate;
	private Date resDate;
	private String resYn;
	
	public MyReservationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MyReservationDTO(int showId, String showName, String userName, int totalPrice, Date showDate, Date resDate,
			String resYn) {
		super();
		this.showId = showId;
		this.showName = showName;
		this.userName = userName;
		this.totalPrice = totalPrice;
		this.showDate = showDate;
		this.resDate = resDate;
		this.resYn = resYn;
	}

	public int getShowId() {
		return showId;
	}
	
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}

	public String getResYn() {
		return resYn;
	}

	public void setResYn(String resYn) {
		this.resYn = resYn;
	}

	@Override
	public String toString() {
		return "MyReservationDTO [showId=" + showId + ", showName=" + showName + ", userName=" + userName
				+ ", totalPrice=" + totalPrice + ", showDate=" + showDate + ", resDate=" + resDate + ", resYn=" + resYn
				+ "]";
	}

}
