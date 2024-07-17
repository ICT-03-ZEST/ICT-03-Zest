package pj.mvc.jsp.util;

import java.io.IOException; 
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageNameChange {

	String thumnail;
	String image;
	
	
	public String getThumnail() {
		return thumnail;
	}

	public String getImage() {
		return image;
	}

	public void imageName(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		// 썸네일
		Map<String, String> map = (Map)request.getAttribute("map");
		
			Iterator<String> itr = map.keySet().iterator();
			while(itr.hasNext()) {
				String key = itr.next();
				System.out.println("key: " + key);
			}
			if(map.get("board_thumnail") == null) {
				this.thumnail = request.getParameter("hiddenThumnail");
			}
			else if(map.get("board_thumnail") != null) {
				this.thumnail = "/js_pj_fasticat/resources/upload/" + map.get("board_thumnail");
			}
		
			if(map.get("board_image") == null) {
				this.image = request.getParameter("hiddenImage");
			}
			else if(map.get("board_image") != null) {
				this.image = "/js_pj_fasticat/resources/upload/" + map.get("board_image");
			}
			
			
		
	}
}

