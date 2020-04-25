package naver.ppojoji.blog.web.upload;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import naver.ppojoji.blog.service.FileUploadService;
/**
 * 파일 여러개 딸랑 업로드하는 기능입니다.
 *  /blog/WEB-INF/views/upload/single-file-upload.jsp 
 * @author HA
 *
 */
@Controller
public class AjaxFileUploadController {

	@Autowired
	FileUploadService fileService;
	@RequestMapping(value="/upload/ajax", method = RequestMethod.GET)
	public String pageUpload() {
		// /blog/WEB-INF/views/upload/ajax-file-upload.jsp
		return "upload/ajax-upload";
	}
	
//	@RequestMapping(value="/upload/ajax", method = RequestMethod.POST)
//	@ResponseBody
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
		return "{\"success\": true}";
	}
	@RequestMapping(value="/upload/ajax", method = RequestMethod.POST)
	@ResponseBody
	public String fileSave(
			@RequestParam List<MultipartFile> files) {
		//System.out.println("title:   " + title);
		/*
		System.out.println("# of file: " + files.size());
		for (MultipartFile file : files) {
			System.out.println("type:   " + file.getContentType());
			System.out.println("type:   " + file.getSize());
			System.out.println("type:   " + file.getOriginalFilename());
			// fileService.uploadSave(file); // 1
			System.out.println("===");
		}
		*/
		fileService.uploadSave(-1, files); // 2
		return "{\"success\": true}";
	}
	
}
