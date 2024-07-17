package pj.mvc.jsp.page;

public class BoardPaging {
	
	private int pageSize = 10; 	// 1page당 게시글의 갯수를 지정
	private int count = 0;		// 전체글의 갯수를 저장하는 변수
	private int number = 0;
	private String pageNum; 	// 화면에서 받는 페이지번호
	
	private int startRow;		// 페이지별 시작번호
	private int endRow;			// 페이지별 끝번호
	
	private int currentPage; 	// 현재페이지
	private int pageCount;		// 총 페이지수
	private int startPage;		// 시작 페이지
	private int pageBlock; 		// 보여지는 가운데 페이지번호
	private int endPage;
	
	private int prev;			// 이전
	private int next;			// 다음
	
	//생성자
	public BoardPaging() {}
	
	public BoardPaging(String pageNum) {
		//pageNum이 없는 경우(맨 처음 board_list.jsp를 클릭하거나, 수정 삭제 등 다른 게시글에서 페이지를 클릭할때) 
		//null처리 되므로 1로 설정
		if(pageNum == null) {
			pageNum = "1";
		}
		
		this.pageNum = pageNum;
		
		currentPage = Integer.parseInt(pageNum); //현재페이지
		
		System.out.println("==============");
		System.out.println("pageNum: " + pageNum);
		System.out.println("currentPage: " + currentPage);
	}

	//getter setter -----------
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	
	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
	//---------getter setter 
	
	public void setTotalCount(int count) {
		this.count = count; //전체 게시글 건수
		
		startRow = (currentPage - 1) * pageSize + 1;	// 페이지별 시작번호 => start에 해당 
		endRow = currentPage * pageSize; 				// 페이지별 끝번호 => end에 해당 
		
		System.out.println("startRow: " + startRow); //1 11
		System.out.println("endRow: " + endRow);	//10 20
		
		this.number = count - (currentPage - 1) * pageSize;//페이지 번호(1)
		System.out.println("number: " + number); // 991
		
		//페이지 계산
		pageCalculator();
	}

	private void pageCalculator() {
		
		if(count > 0) {
			pageCount =  count / pageSize + (count % pageSize == 0 ? 0 : 1);
			System.out.println("pageCount : " + pageCount); //100
			
			// 시작 페이지 설정 %3을 하면 1, 4, 7 ...
			if(currentPage % 3 != 0) { //3으로 나눈 나머지가 0이 아닐때
				startPage = (int)(currentPage / 3) * 3 + 1; //=> 현재페이지가 3, 6, 9..가 아닐때 시작페이지  
			}
			else {//3으로 나눈 나머지가 0일 때 - 다음버튼을 누른다음에 나타나는 시작하는 페이지 => 현재페이지가 3, 6, 9..일때 시작페이지
				startPage = ((int)(currentPage / 3) -1) * 3 + 1;
			}
			
			System.out.println("startPage : " + startPage); 
			
			pageBlock = 3;//이전과 다음사이에 보여지는 페이지수 startPage와 관련있음 [이전] 456 [다음] 3개
			endPage = startPage + pageBlock - 1;//마지막페이지가 시작페이지를 기준으로 계산됨 3 , 6 , 9...
			
			//마지막 페이지번호가 총 페이지수보다 클때 
			if(endPage > pageCount) endPage = pageCount;
			
			 // 이전과 다음버튼이 페이지번호가됨
	         // 이전
			if(startPage > 3) prev = startPage - 1;	// [이전] 456 [다음]에서 [이전]이 3이되어야함
			
			// 다음
			if(startPage < pageCount) next = startPage + 3;//123[다음] 에서 [다음]이 4가되도록함
			
			System.out.println("prev : " + prev);
	        System.out.println("next : " + next);
			
		}
	}
}
