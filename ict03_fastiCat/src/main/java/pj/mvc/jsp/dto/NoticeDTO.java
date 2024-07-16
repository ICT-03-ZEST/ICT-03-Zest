package pj.mvc.jsp.dto;

import java.sql.Date;

public class NoticeDTO {
	
	private int N_Board_Num;	//공지글 번호
	private String N_Title;		//공지 제목
	private String N_Content;	//공지 내용
	private String N_Writer;	//작성자
	private Date N_WriteDate;	//작성일
	private int N_ReadCnt;		//조회수
	
	//디폴트생성자 
	public NoticeDTO() {
		
	}
	//매개변수생성자
	public NoticeDTO(int n_Board_Num, String n_Title, String n_Content, String n_Writer, Date n_WriteDate,
			int n_ReadCnt) {
		super();
		N_Board_Num = n_Board_Num;
		N_Title = n_Title;
		N_Content = n_Content;
		N_Writer = n_Writer;
		N_WriteDate = n_WriteDate;
		N_ReadCnt = n_ReadCnt;
	}
	
	//getter setter
	public int getN_Board_Num() {
		return N_Board_Num;
	}
	public void setN_Board_Num(int n_Board_Num) {
		N_Board_Num = n_Board_Num;
	}
	public String getN_Title() {
		return N_Title;
	}
	public void setN_Title(String n_Title) {
		N_Title = n_Title;
	}
	public String getN_Content() {
		return N_Content;
	}
	public void setN_Content(String n_Content) {
		N_Content = n_Content;
	}
	public String getN_Writer() {
		return N_Writer;
	}
	public void setN_Writer(String n_Writer) {
		N_Writer = n_Writer;
	}
	public Date getN_WriteDate() {
		return N_WriteDate;
	}
	public void setN_WriteDate(Date n_WriteDate) {
		N_WriteDate = n_WriteDate;
	}
	public int getN_ReadCnt() {
		return N_ReadCnt;
	}
	public void setN_ReadCnt(int n_ReadCnt) {
		N_ReadCnt = n_ReadCnt;
	}
	
	//String ToString
	@Override
	public String toString() {
		return "NoticeDTO [N_Board_Num=" + N_Board_Num + ", N_Title=" + N_Title + ", N_Content=" + N_Content
				+ ", N_Writer=" + N_Writer + ", N_WriteDate=" + N_WriteDate + ", N_ReadCnt=" + N_ReadCnt + "]";
	}
}
