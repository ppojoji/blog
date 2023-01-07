package naver.ppojoji.blog.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.Error;
import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.LocalUpFile;
import naver.ppojoji.blog.dto.Mail;
import naver.ppojoji.blog.dto.Note;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.mail.MailingService;
import naver.ppojoji.blog.service.oauth.OAuthService;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	OAuthService oauthService;
	@Autowired
	MailingService mailingService;
	@Autowired
	FileUploadService fileService;
	@Autowired
	TagService tagService;
	@Autowired
	BlogService blogService;
	
	@Value("${blog.user.pic}")
	String profileRootDir;
	
	public User login(String id , String pwd, String useCookie) {
		
		User user = userDao.login(id, pwd);
		
		if (user != null && "true".equals(useCookie)) {
			// generate uuid 
			String autoLoginKey = UUID.randomUUID().toString();
			userDao.updateAutoLoginKey(user.getSeq(), autoLoginKey);
			user.setAutoLoginKey(autoLoginKey);
			//user.setAutoLoginKey(autoLoginKey);
			
		}
		System.out.println("로그인? " + user);
		return user;  
	}
	public LocalUpFile readProfile(String profileFileName) {
		File localFile = new File(profileRootDir,profileFileName);
		
		LocalUpFile upfile = new LocalUpFile();
		try {
			String contentType = Files.probeContentType(localFile.toPath());
			upfile.setContentType(contentType);
			byte [] fileContents = Files.readAllBytes(localFile.toPath());
			upfile.setContent(fileContents);
			return upfile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User loginByOAuth(String accessToken) {
		
		String email = oauthService.findUserEmail(accessToken);
		
		if(email != null) {
			return userDao.findUserByEmail(email);
		} else {
			return null;
		}
	}
	public void userDelete(User user , String email) {
		if(user.getEmail().equals(email)) {
			// 2. tb_files 에 탈퇴한 사용자가 쓴 글에 첨부된 파일 삭제
			List<LocalUpFile> files = fileService.findUserfiles(user.getSeq());
			for (LocalUpFile localUpFile : files) {
				fileService.deleteFile(localUpFile.getGenName());
			}
			// 3. user.getSeq() => 글들을 가져와야함! tb_posttag 에서 삭제된 글과 연결된 태그들 삭제
			List<Post> posts = blogService.findPostsByWriter(user.getSeq()); // tagService.findTagsByPost(user.getSeq());
			for (Post post : posts) {
				tagService.updateTags(Collections.emptyList(), post.getSeq());
			}
			userDao.userDelete(user,email);
		}else {
			throw new BlogException(400, "EMAIL_MISMATCH");
		}
	}
	
	public void resetPassword(String email) {
		/*
		 * 1) 베일로 비번을 쏴줌(임시비번)
		 * 
		 * 2) 비번 재설정 url을 전송함
		 * 
		 */
		User user = userDao.findUserByEmail(email);
		String pwd = UUID.randomUUID().toString();
		pwd = pwd.substring(0, 20);
		
		user.setPwd(pwd);
		
		userDao.UpdatePwd(user.getEmail(), user.getPwd());
		
		String title = "비번 변경됨";
		String content = "임시 비번을 설정했습니다. [" + pwd + "]";
		String receiver = user.getEmail();
		Mail mail = new Mail(title, content, receiver);
		mailingService.SendMail(mail);
		
		
	}

	public User join(String id, String email, String pwd, String pwhint, String pwhintans) {
		// 올바른 email 형식 ??
		// 비번이 없는 경우 막아야 함??
		// email 중복 여부 확인???
		// 1. null이 아니어야 함 - id, email, pwd
		// 2. 길이 확인해야함 - pwd, id
		/*
		 * ^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])* 
		 * @
		 * [0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$
		 */
		String pattern2 = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		if(!Pattern.matches(pattern2, email)) {
			throw new BlogException(400, "NO_CORRECT_EMAIL");
		}
		Util.notEmpty(email, "NO_EMAIL");
		Util.notEmpty(id, "NO_ID");
		Util.notEmpty(id, "NO_ID");
		Util.notEmpty(pwd, "NO_PWD");
		Util.notEmpty(pwhint, "NO_HINT");
		Util.notEmpty(pwhintans, "NO_HINT_ANSWER");
		
		if(id.length() < 2) {
			throw new BlogException(400, "ID_TO_SHORT");
		}
		
		if (pwd.length() < 6) {
			throw new BlogException(400, "PW_TO_SHORT");
		}
		if (pwd.length() > 12) {
			throw new BlogException(400, "PW_TO_LONG");
		}
		
		
		User user = userDao.findUserById(id);
		
		if(user != null) {
			throw new BlogException(400, "DUP_ID");
		}
		user = new User();
		user.setAdmin("N");
		user.setEmail(email);
		user.setId(id);
		user.setPwd(pwd);
		user.setPwhint(pwhint);
		user.setPwhintans(pwhintans);
		
		// System.out.println(id + " , " + email+ " , " +pwd + " , " + pwhint + " , " + pwhintans); 
		userDao.join(user);
		return user;
	}

	public void checkProp(String prop, String value) {
		if ("id".equals(prop)) {
			User user = userDao.findUserById(value);			
			if(user != null) {
				throw new BlogException(400, "DUP_ID");
			}
		}
		else if("email".equals(prop)) {
			User user = userDao.findUserByEmail(value);
			if(user != null) {
				throw new BlogException(400, "DUP_EMAIL");
			}
		}
		else {
			throw new BlogException(400, "NO_SUCH_PROP");
		}
		
	}

//	public void updatePwd(String email, String hint, String hintAns) {
//		User user = new User();
//		user = userDao.findUserByEmail(email);
//		
//		if(user != null) {
////			userDao.
//		}
//	}
	public User hint(String email, String hint, String hintAns) {
		// 1. 가짜 유저
		User searchOption =new User();
		searchOption.setEmail(email);
		searchOption.setPwhint(hint);
		searchOption.setPwhintans(hintAns);
		
		User realUser = userDao.hint(searchOption);
		
		if(realUser != null) {
			String pwd = UUID.randomUUID().toString();
			pwd = pwd.substring(0, 6);
			// 1. 찾았음.
			String newPassword = pwd; // FIXME 랜덤하게 바꿔야 함
			userDao.UpdatePwd(email, newPassword );
			realUser.setPwd(newPassword);
			return realUser;
		} else {
			// 뭔가 틀림!
			throw new BlogException(404, "NO_SEARCH_USER");
		}
	}

	public void UpdateReadNote(Integer userSeq, Integer readNote) {
		User user = userDao.findUser(userSeq);
		userDao.updateReadNote(user,readNote);
	}
	/**
	 * pk로 사용자 조회함
	 * @param userSeq
	 * @return
	 */
	public User findBySeq(Integer userSeq) {
		User user = userDao.findUser(userSeq);
		return user;
	}
	public String updateProfile(Integer userSeq , MultipartFile profile) {
		// TODO Auto-generated method stub
		User user = userDao.findUser(userSeq);
		
		try {
			
			// 2.1. 현재 프로필 사진을 지워야 함
			if(user.getUserpic() != null) {
				File currentFile   = new File(profileRootDir, user.getUserpic());
				if (currentFile.exists()) {
					currentFile.delete();
				}
			}
			
			// 2.2 임의의 파일 이름을 생성해야 함!
			String genFileName = UUID.randomUUID().toString();
			
			// 2.3 위에서 생성한 이름으로 파일을 저장함
			byte[] data = profile.getBytes();
			File localFile = new File(profileRootDir, genFileName);
			localFile.createNewFile();
			
			Path filepath = localFile.toPath();
			Files.write(filepath,data, StandardOpenOption.TRUNCATE_EXISTING);
						
			// 3. 디비 업데이트( userpic 컬럼..)
			user.setUserpic(genFileName);
			userDao.updateProfile(user);
			
			return genFileName;
			
		}catch(IOException e) {
			throw new BlogException(500, "SERVER_ERROR");
		}
	}
}
