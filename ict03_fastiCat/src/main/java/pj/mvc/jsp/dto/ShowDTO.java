package pj.mvc.jsp.dto;

import java.sql.Date;

public class ShowDTO {
	private int showNum;         // 공연번호
    private String showName;     // 공연명
    private String showPlace;    // 공연장소
    private int showPrice;       // 1매당 가격
    private int curCapacity;     // 현수용인원
    private int maxCapacity;     // 최대수용인원
    private Date showDay;        // 공연날짜
    private String showCHK;      // 활성화 유무 y/n
	
    public ShowDTO() {}

	public ShowDTO(int showNum, String showName, int curCapacity, int maxCapacity, Date showDay, String showCHK) {
		super();
		this.showNum = showNum;
		this.showName = showName;
		this.curCapacity = curCapacity;
		this.maxCapacity = maxCapacity;
		this.showDay = showDay;
		this.showCHK = showCHK;
	}

	public ShowDTO(int showNum, String showName, String showPlace, int showPrice, int curCapacity, int maxCapacity,
			Date showDay, String showCHK) {
		super();
		this.showNum = showNum;
		this.showName = showName;
		this.showPlace = showPlace;
		this.showPrice = showPrice;
		this.curCapacity = curCapacity;
		this.maxCapacity = maxCapacity;
		this.showDay = showDay;
		this.showCHK = showCHK;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getShowPlace() {
		return showPlace;
	}

	public void setShowPlace(String showPlace) {
		this.showPlace = showPlace;
	}

	public int getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(int showPrice) {
		this.showPrice = showPrice;
	}

	public int getCurCapacity() {
		return curCapacity;
	}

	public void setCurCapacity(int curCapacity) {
		this.curCapacity = curCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Date getShowDay() {
		return showDay;
	}

	public void setShowDay(Date showDay) {
		this.showDay = showDay;
	}

	public String getShowCHK() {
		return showCHK;
	}

	public void setShowCHK(String showCHK) {
		this.showCHK = showCHK;
	}

	@Override
	public String toString() {
		return "ShowDTO [showNum=" + showNum + ", showName=" + showName + ", showPlace=" + showPlace + ", showPrice="
				+ showPrice + ", curCapacity=" + curCapacity + ", maxCapacity=" + maxCapacity + ", showDay=" + showDay
				+ ", showCHK=" + showCHK + "]";
	}

	
    
}
