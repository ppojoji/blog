package naver.ppojoji.blog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import naver.ppojoji.blog.dto.LocalUpFile;

@Repository
public class FileDao {
	@Autowired 
	SqlSession session;
	public int insertFile(MultipartFile file, Integer postSeq, String generatedName) {
		Map<String, Object> map = new HashMap<String, Object>();
			map.put("originName", file.getOriginalFilename());
			map.put("genName", generatedName);
			map.put("fileSize", file.getSize());
			map.put("contentType", file.getContentType());
			map.put("post",postSeq);
		return session.insert("FileMapper.fileSave",map);
	}
	public LocalUpFile FindFile(String genName) {
		return session.selectOne("FileMapper.FindFile", genName);
	}
	public int deleteFile(LocalUpFile file) {
		return session.delete("FileMapper.deleteFile" , file);
	}
}
