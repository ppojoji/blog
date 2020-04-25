package naver.ppojoji.blog.web.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
 * 파일 하나 딸랑 업로드하는 기능입니다.
 *  /blog/WEB-INF/views/upload/single-file-upload.jsp 
 * @author HA
 *
 */
@Controller
public class SingleFileUploadController {

	@RequestMapping(value="/upload/single", method = RequestMethod.GET)
	public String pageUpload() {
		return "upload/single-file-upload";
	}
	
	@RequestMapping(value="/upload/single", method = RequestMethod.POST)
	public String fileUpload(
			@RequestParam String title, 
			@RequestParam("file") MultipartFile file) {
		System.out.println("title:   " + title);
		System.out.println("type:   " + file.getContentType());
		System.out.println("type:   " + file.getSize());
		System.out.println("type:   " + file.getOriginalFilename());
		return "redirect:/upload/single";
	}
}
