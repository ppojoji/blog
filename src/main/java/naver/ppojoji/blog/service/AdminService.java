package naver.ppojoji.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.AdminDao;

@Service
public class AdminService {

	@Autowired
	AdminDao adminDao;
	
}
