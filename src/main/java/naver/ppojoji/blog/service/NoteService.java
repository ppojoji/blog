package naver.ppojoji.blog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ErrorCoded;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.Error;
import naver.ppojoji.blog.dao.NoteDao;
import naver.ppojoji.blog.dto.Note;
import naver.ppojoji.blog.dto.User;

@Service
@Transactional
public class NoteService {

	@Autowired
	NoteDao noteDao;
	
	public Note createNote(Note note) {
		// 1. sender 있는지 확인
		// 2. receiver 있는지 확인
		// 3.  content null 이면 안됨. 빈문자열 "" 도 안됨!
		//     500자 초과했는지 확인해야함
		// validation 로직
		
		System.out.println(" ###### [NOTE] " + note.toString());
		
		if(note.getSender() == null) {
			throw new BlogException(401, "NOT_FOUND_SENDER");
		}else if(note.getReceiver() == null) {
			throw new BlogException(401, "NOT_FOUND_RECEIVER");
		}else if(note.getContent().equals("") || note.getContent().equals(null)) {
			throw new BlogException(401, "NOT_CONTENT");
		}
//		else(note.getContent().length() > 500) {
//			throw new BlogException(401, "TOO_LONG");
//		}
		 
		note.setSendTime(new Date());
		note.setSender_Delete("N");
		note.setReceiver_Delete("N");
		noteDao.createNote(note);

		return note;
	}
	/**
	 * 새로운 쪽지 갯수 반환
	 * @param loginUser
	 * @return
	 */
	public Integer countNewNotes(User loginUser) {
		return noteDao.countNewNotes(loginUser.getSeq());
	}
	
	public List<Note> loadSendNote(Integer sender) {
		return noteDao.loadSendNote(sender);
	}
	
	public List<Note> loadReceiverNote(Integer receiver) {
		return noteDao.loadReceiverNote(receiver);
	}
	
	public Note readNote(Integer receiverSeq, Integer noteSeq) {
		// 1. 자기 쪽지인지 확인 - 해결됨
		// 2. 읽지 않은 쪽지면 읽은 시간을 업데이트 쳐줌(이미 읽었으면 업데이트 하면 안됨)
		Note note = noteDao.readNote(receiverSeq,noteSeq);
		if(note.getReadTime() == null) {
			note.setReadTime(new Date());
			noteDao.updateReadTime(note);
		}
		return note;
	}
	
	public Note SenderNote(Integer senderSeq, Integer noteSeq) {
		Note note = noteDao.SenderNote(senderSeq,noteSeq);
		
		return note;
	}
	public Note deleteNote(String mode, Integer userSeq, Integer noteSeq) {
		/*
		 * mode === S userSeq => sender
		 * mode === R userSeq => receiver
		 */
		Note note = null;
		if (mode.equals("S")) {
			note = noteDao.SenderNote(userSeq, noteSeq);
		}else if(mode.equals("R")) {
			note = noteDao.readNote(userSeq, noteSeq);
		}else {
			throw new BlogException(400, Error.BAD_REQUEST);
		}
		noteDao.markAsDelete(note, mode);
		return note;
	}
	

}
