package pj.mvc.jsp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// 전체 이미지 경로에서 이미지파일명(김치냉장고.jpg)을 잘라와서 setAttribute로 전달(45행 setAttribute 참조)
public class BoardImageUploadHandler { 

	private String uploadPath; //이미지 경로
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public void imageUpload(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		// import java.io.file
		File uploadDir = new File(uploadPath);
		
		// 업로드할 경우에 폴더가 없는 경우 폴더 생성
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			uploadDir.setWritable(true);
			uploadDir.setReadable(true);
			uploadDir.setExecutable(true);
			
			 Map<String, String> map = new HashMap<>(); 
			 String fileName = "";
			 
	//Part 클래스는 Java Servlet API에서 제공하는 인터페이스로, 
	//HTTP 요청의 일부로 전송된 파일이나 폼 데이터와 같은 멀티파트 요청의 각 부분을 나타냅니다. 
	//주로 파일 업로드와 관련된 기능을 구현할 때 사용됩니다.		
			for(Part part : req.getParts()) {
				System.out.println(part.getHeader("content-disposition"));
			
				String name = part.getName();
				fileName = getFileName(part); // 아래 생성한 메서드 호출
				
				if(fileName != null && !"".contentEquals(fileName)) {
					System.out.println("fileName: " + fileName);
					part.write(uploadPath + File.separator + fileName);
					
					if(name.equals("board_thumnail")) {
						map.put("board_thumnail", fileName);
					}
					else {
						map.put("board_image", fileName);
					}
				}
			}
			req.setAttribute("map", map);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		res.setContentType("text/html; charset=UTF-8");
		res.getWriter().println("==업로드 완료==");
	}
	//선택한 이미지(예: 트롬세탁기.jpg) 리턴
	private String getFileName(Part part) {
		
		for(String content : part.getHeader("content-disposition").split(";")) {
			
			// [ form-data; name="pdImg"; filename="김치냉장고.jpg" ] => ;으로 자른다
	         // [ filename="김치냉장고.jpg" ] => 김치냉장고.jpg 가져오기
			
			if(content.trim().startsWith("filename")) { // filename으로 시작하는 부분
				System.out.println("content : " + content);
				
				// [ filename="김치냉장고.jpg" ] => + 2는 (=")를 의미  |  -1은 끝(") 버리기

	            // content : filename=트롬세탁기.jpg  => 트롬세탁기.jpg를 리턴
				return content.substring(content.indexOf("=") + 2, content.length() - 1); //트롬세탁기.jpg
			}									//= 으로 시작하는 부분에서 2번째자리, - 1 : ' " ' 빼고 가져오기 
		}
		
		return null;//filename이 없는경우(폼필드가 데이터인 경우)
	}
	
}
