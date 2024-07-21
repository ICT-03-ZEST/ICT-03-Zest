package pj.mvc.jsp.dto;

/* 작성자 : 방준성
 * 작성일 : 24.07.21
 * 설명 : show_Reservation 테이블의 dto파일
 * show_ResId  NUMBER(6)           PRIMARY KEY,
	showNum     NUMBER(6)           REFERENCES show_tbl(showNum),
	userID      VARCHAR2(20char)    REFERENCES MVC_CUSTOMER_TBL(USERID),
	password    VARCHAR2(20char)    NOT NULL,
	totalPrice  NUMBER(20)									*/
public class Show_ReservationDTO {
	
	private int show_ResId;
	private int showNum;
	private String userID;
	private String password;
	private int totalPrice;
	
	public Show_ReservationDTO() { }

	public Show_ReservationDTO(int show_ResId, int showNum, String userID, String password, int totalPrice) {
		super();
		this.show_ResId = show_ResId;
		this.showNum = showNum;
		this.userID = userID;
		this.password = password;
		this.totalPrice = totalPrice;
	}

	public int getShow_ResId() {
		return show_ResId;
	}

	public void setShow_ResId(int show_ResId) {
		this.show_ResId = show_ResId;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Show_Reservation [show_ResId=" + show_ResId + ", showNum=" + showNum + ", userID=" + userID
				+ ", password=" + password + ", totalPrice=" + totalPrice + "]";
	}

	
}
