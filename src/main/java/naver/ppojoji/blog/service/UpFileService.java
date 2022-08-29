package naver.ppojoji.blog.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.FileDao;
import naver.ppojoji.blog.dto.LocalUpFile;

/**
 *@deprecated 중복!!!!
 * @author ppojo
 *
 */
@Service
public class UpFileService {
	@Autowired
	FileDao fileDao;

	@Value("${blog.upfile.root}")
	String rootDir;
	
	public LocalUpFile FindFile(String genName) {
		return fileDao.FindFile(genName);
	}
	/**
	 * 주어진 이름의 파일을 읽어서 바이트 정보를 반환합니다.
	 * @param genName
	 * @return
	 */
	public byte[] readLocalFile(String genName) {
		File localFile = new File(rootDir,genName);
		try {
			byte [] fileContents = Files.readAllBytes(localFile.toPath());
			return fileContents;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public LocalUpFile deleteFile(String genName) {
		System.out.println("[GEN NAME]" + genName);
		LocalUpFile file = fileDao.FindFile(genName);
		
		String subPath = file.getGenName();
		String filePath = rootDir + "/" + subPath; // 
		
		File f = new File(filePath);
		if (f.exists()) {
			boolean done = f.delete(); // 진짜 디스크에서 지움
			System.out.println("del? " + done);
		}
		int delCnt = fileDao.deleteFile(file);
		System.out.println("[del cnt]" + delCnt);
		return file;
	}
	
}
