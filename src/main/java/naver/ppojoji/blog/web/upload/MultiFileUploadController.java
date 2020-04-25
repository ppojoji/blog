package naver.ppojoji.blog.web.upload;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
 * 파일 여러개 딸랑 업로드하는 기능입니다.
 *  /blog/WEB-INF/views/upload/single-file-upload.jsp 
 * @author HA
 *
 */
@Controller
public class MultiFileUploadController {

	@RequestMapping(value="/upload/multi", method = RequestMethod.GET)
	public String pageUpload() {
		return "upload/multi-file-upload";
	}
	
	@RequestMapping(value="/upload/multi", method = RequestMethod.POST)
	public String fileUpload(
			@RequestParam String title, 
			@RequestParam List<MultipartFile> files) {
		System.out.println("title:   " + title);
		
		System.out.println("# of file: " + files.size());
		for (MultipartFile file : files) {
			System.out.println("type:   " + file.getContentType());
			System.out.println("type:   " + file.getSize());
			System.out.println("type:   " + file.getOriginalFilename());
			System.out.println("===");
		}
		return "redirect:/upload/multi";
	}
}
