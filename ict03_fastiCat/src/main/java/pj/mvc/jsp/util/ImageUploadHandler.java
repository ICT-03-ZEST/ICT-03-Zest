package pj.mvc.jsp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//전체 이미지 경로에서 이미지파일명(예:김치냉장고.jpg)을 잘라와서 setAttribute로 전달(46행 참조)
public class ImageUploadHandler {
	
	private String uploadPath;		//이미지 경로
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	public void imageUpload(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		// import java.io.File;
		File uploadDir = new File(uploadPath);
		
		// 업로드할 경우에 폴더가 없는 경우 폴더 생성
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			uploadDir.setWritable(true);
			uploadDir.setReadable(true);
			uploadDir.setExecutable(true);
			
			String fileName = "";
			for(Part part : req.getParts()) {
				System.out.println(part.getHeader("content-disposition"));
				
				fileName = getFileName(part);  // 아래 생성한 메서드 호출
				if(fileName != null && !"".contentEquals(fileName)) {
					part.write(uploadPath + File.separator + fileName);
					
					System.out.println("fileName : " + fileName);
					req.setAttribute("fileName", fileName); // 서비스에서 getAttribute("fileName");
				}
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		res.setContentType("text/html; charset=UTF-8");
		res.getWriter().println("==== 업로드 완료 !! ====");
	}
	
	//선택한 이미지(예 : 트롬세탁기.jpg) 리턴
	private String getFileName(Part part) {
		
		for(String content : part.getHeader("content-disposition").split(";")) {
			
			// [ form-data; name="pdImg"; filename="김치냉장고.jpg" ] => ;으로 자른다
	        // [ filename="김치냉장고.jpg" ] => 김치냉장고.jpg 가져오기
			
			
			if(content.trim().startsWith("filename")) {
				System.out.println("content : " + content);
				
				// [ filename="김치냉장고.jpg" ] => + 2는 (=")를 의미  |  -1은 끝(") 버리기

	            // content : filename=트롬세탁기.jpg  => 트롬세탁기.jpg를 리턴
				
				return content.substring(content.indexOf("=") + 2, content.length() -1); //트롬세탁기.jpg
			}
		}
		
		return null;	// filename이 없는 경우 ( 폼필드가 데이터인 경우)
	}
}
