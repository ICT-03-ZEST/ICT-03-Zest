package pj.mvc.jsp.dto;


public class ReservationDTO22 {
	
	private int resNum;			//예약번호
	private String resName;		//티켓명
	private String customer;	//고객명
	private String customerPhon;//고객전화번호
	private String customerAddr;//고객 주소
	private int place;			//가격
	private int deadline;		//마감인원
	private int maxDeadline;
	private String resDay;		//예약일
	private String show;			//마감 후 감추기
	
	public ReservationDTO22() {}

	public ReservationDTO22(int resNum, String resName, String customer, String customerPhon, String customerAddr,
			int place, int deadline, int maxDeadline, String resDay, String show) {
		super();
		this.resNum = resNum;
		this.resName = resName;
		this.customer = customer;
		this.customerPhon = customerPhon;
		this.customerAddr = customerAddr;
		this.place = place;
		this.deadline = deadline;
		this.maxDeadline = maxDeadline;
		this.resDay = resDay;
		this.show = show;
	}

	public int getResNum() {
		return resNum;
	}

	public void setResNum(int resNum) {
		this.resNum = resNum;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomerPhon() {
		return customerPhon;
	}

	public void setCustomerPhon(String customerPhon) {
		this.customerPhon = customerPhon;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getMaxDeadline() {
		return maxDeadline;
	}

	public void setMaxDeadline(int maxDeadline) {
		this.maxDeadline = maxDeadline;
	}

	public String getResDay() {
		return resDay;
	}

	public void setResDay(String resDay) {
		this.resDay = resDay;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "ReservationDTO [resNum=" + resNum + ", resName=" + resName + ", customer=" + customer
				+ ", customerPhon=" + customerPhon + ", customerAddr=" + customerAddr + ", place=" + place
				+ ", deadline=" + deadline + ", maxDeadline=" + maxDeadline + ", resDay=" + resDay + ", show=" + show
				+ "]";
	}

	
}
