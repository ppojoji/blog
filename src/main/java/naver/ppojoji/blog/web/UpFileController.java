package naver.ppojoji.blog.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import naver.ppojoji.blog.dto.LocalUpFile;
import naver.ppojoji.blog.service.UpFileService;

@Controller
public class UpFileController {
	/*
	 * 
	 *  /upfile/6161c120-1618-4c17-b8f9-4a760a20592e
	 */
	@Autowired 
	UpFileService upFileService;
	@RequestMapping(value = "/upfile/{genName}" ,method = RequestMethod.GET)
	public String UpFile(@PathVariable String genName,
			HttpServletResponse res) throws IOException {
		LocalUpFile file = null; // req
		// /blog/WEB-INF/views/upfile/6161c120-1618-4c17-b8f9-4a760a20592e.jsp
		file = upFileService.FindFile(genName);
		if(file == null)
		{
			res.setStatus(404);
			return "error/404";
			// 404 응답을 전송합니다.
		} else {
			System.out.println(file);
			
			// File IO 입출력 작업을 해야합니다.
			/*
				StringBuilder sb = new StringBuilder();
				sb.append("<html><body>");
				sb.append("<h3>FILE FILE</h3>");
				sb.append("</body><html>");
				byte [] data = sb.toString().getBytes();
				
				npe
				
				참조변수 == null
			 * 참조변수.메소드호출()
			 * 참조변수.필드;
		
			 */
			byte [] data = upFileService.readLocalFile(file.getGenName());
			OutputStream out = res.getOutputStream();
			/*
			 * 1. 브라우저한테 지금 보내는 바이트의 타입이 뭔지... 
			 */
			res.setContentType(file.getContentType());
			/*
			 * 2. 반드시 데이터의 크기를 알려줘야 함!!
			 */
			res.setContentLength(data.length);
			IOUtils.write(data, out);
			out.flush(); // 버퍼에 남아있는 데이터 전부 내려보내라!
			return null;
		}
		
	}
}
