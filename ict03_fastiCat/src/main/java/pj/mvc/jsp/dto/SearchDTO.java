package pj.mvc.jsp.dto;

import java.sql.Date;

public class SearchDTO {
	private int num;                                         
	private String title;          //   글제목                    
	private String content;        //   글내용        
	private String writer;         //   작성자       
	private String password;       //   수정, 삭제용 비밀번호
	private int readCnt;          //   조회수         
	private Date regDate;          //   작성일        
	private int comment_count;  	//   댓글갯수   
	private String searchInput;		// 검색값
    private String source; // 추가된 필드


	
	public SearchDTO(int num, String title, String content, String writer, String password, int readCnt, Date regDate,
			int comment_count, String searchInput) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.readCnt = readCnt;
		this.regDate = regDate;
		this.comment_count = comment_count;
		this.searchInput = searchInput;
	}

	public SearchDTO() {
		super();
	}

	public String getSearchInput() {
		return searchInput;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
}
