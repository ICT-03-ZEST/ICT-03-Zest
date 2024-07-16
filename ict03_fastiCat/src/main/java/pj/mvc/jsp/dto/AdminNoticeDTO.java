package pj.mvc.jsp.dto;

import java.sql.Date;

public class AdminNoticeDTO {

	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeImg;
	private String noticeWriter;
	private int noticeReadCnt;
	private Date noticeRegDate;
	
	public AdminNoticeDTO() {}
	
	public AdminNoticeDTO(int noticeNo, String noticeTitle, String noticeContent, String noticeImg, String noticeWriter,
			int noticeReadCnt, Date noticeRegDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeImg = noticeImg;
		this.noticeWriter = noticeWriter;
		this.noticeReadCnt = noticeReadCnt;
		this.noticeRegDate = noticeRegDate;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeImg() {
		return noticeImg;
	}

	public void setNoticeImg(String noticeImg) {
		this.noticeImg = noticeImg;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public int getNoticeReadCnt() {
		return noticeReadCnt;
	}

	public void setNoticeReadCnt(int noticeReadCnt) {
		this.noticeReadCnt = noticeReadCnt;
	}

	public Date getNoticeRegDate() {
		return noticeRegDate;
	}

	public void setNoticeRegDate(Date noticeRegDate) {
		this.noticeRegDate = noticeRegDate;
	}

	@Override
	public String toString() {
		return "AdminNoticeDTO [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent="
				+ noticeContent + ", noticeImg=" + noticeImg + ", noticeWriter=" + noticeWriter + ", noticeReadCnt="
				+ noticeReadCnt + ", noticeRegDate=" + noticeRegDate + "]";
	}
	
}
