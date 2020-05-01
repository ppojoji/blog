package naver.ppojoji.blog.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.FileDao;
import naver.ppojoji.blog.dto.LocalUpFile;

@Service
public class UpFileService {
	@Autowired
	FileDao fileDao;

	String rootDir = "D:\\uproot\\blog";
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
	
}
