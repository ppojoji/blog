package naver.ppojoji.blog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import naver.ppojoji.blog.dao.FileDao;
import naver.ppojoji.blog.dto.LocalUpFile;

@Service
public class FileUploadService {
	@Autowired 
	FileDao fileDao;
	
	String rootDir = "D:\\uproot\\blog";
	public String uploadSave(Integer postSeq, List<MultipartFile> files) {
		for(MultipartFile file : files) {
			// 1. 유일한 이름을 가짜로 생성함 - genName
			String originName = file.getOriginalFilename();
			String genName = UUID.randomUUID().toString(); // 
			// 2. genName 으로 특정 디렉토리에 파일을 생성함
			// String genName = originName + "-user" + "yyyy-mm-dd_hhmmss";
			try {
				byte [] data = file.getBytes();
				// 2.1. 파일부터 생성합니다.
				File localFile = new File(rootDir, genName);
				localFile.createNewFile();
				
				// 2.2. 파일을 저장하는 메소드를 호출함
				Path filepath = localFile.toPath();
				Files.write(filepath,data, StandardOpenOption.TRUNCATE_EXISTING);
				 fileDao.insertFile(file, postSeq, genName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
//	public void deleteFile(String genName) {
//		File file = new File(rootDir, genName);
//		file.delete();
//	}
	public LocalUpFile deleteFile(String genName) {
		System.out.println("[GEN NAME]" + genName);
		LocalUpFile file = fileDao.FindFile(genName);
		
		String subPath = file.getGenName();
		String filePath = rootDir + "\\" + subPath; // 
		
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
