package naver.ppojoji.blog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		String content = note.getContent();
		
		if(note.getSender() == null) {
			throw new BlogException(401, "NOT_FOUND_SENDER");
		}
		if(note.getReceiver() == null) {
			throw new BlogException(401, "NOT_FOUND_RECEIVER");
		}
		if(content.equals("") || content.equals(null)) {
			throw new BlogException(401, "NOT_CONTENT");
		}
		if(content.length() > 500) {
			throw new BlogException(401, "TOO_LONG");
		}
		 
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
	/**
	 * 글지수가 limit 이상이면 limit 사이즈로 잘라냄
	 * @param notes
	 * @param limit
	 */
	private void stripContent(List<Note> notes, int limit) {
		notes.forEach((note) ->{
			if(note.getContent().length() > limit) {
				String content = note.getContent().substring(0,limit);
				note.setContent(content + "..."); 
			}
			
		});
	}
	public List<Note> loadSendNote(Integer sender) {
		List<Note> notes = noteDao.loadSendNote(sender);
		this.stripContent(notes, 20);
		return notes;
	}
	
	public List<Note> loadReceiverNote(Integer receiver) {
		List<Note> notes = noteDao.loadReceiverNote(receiver);
		this.stripContent(notes, 20);
		return notes;
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
			note.setSender_Delete("Y");
		}else if(mode.equals("R")) {
			note = noteDao.readNote(userSeq, noteSeq);
			note.setReceiver_Delete("Y");
		}else {
			throw new BlogException(400, Error.BAD_REQUEST);
		}
		noteDao.markAsDelete(note);
		// 
		if(note.getSender_Delete().equals("Y") && note.getReceiver_Delete().equals("Y")) {
			noteDao.deleteNote(noteSeq);
		} else if(mode.equals("S") && note.getReadTime() == null) {
			noteDao.deleteNote(noteSeq);
		}
		return note;
	}
	public List<Note> queryMessage(Integer userSeq, Integer maxSeq) {
		Date current = new Date();
		return noteDao.queryMessage(userSeq, current,maxSeq);
	}
	

}
