package naver.ppojoji.blog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.Note;

@Repository
public class NoteDao {

	@Autowired
	SqlSession session;
	
	public void createNote(Note note) {
		session.insert("NoteMapper.createNote",note);
	}
	/**
	 * 새로운 쪽지 갯수 조회
	 * @param receiverSeq
	 * @return
	 */
	public Integer countNewNotes(Integer receiverSeq) {
		/*
		 * FIXME 현재 모든 쪽지를 다 던짐. "읽지 않은 쪽지만" 던져야 함
		 */
		return session.selectOne("NoteMapper.countNewNotes", receiverSeq);
	}
	public List<Note> loadSendNote(Integer sender) {
		return session.selectList("NoteMapper.loadSendNote",sender);
	}
	public List<Note> loadReceiverNote(Integer receiver) {
		return session.selectList("NoteMapper.loadReceiverNote",receiver);
	}
	public Note readNote(Integer receiverSeq, Integer noteSeq) {
		
		Map<String,Integer> map = new HashMap<>();
		map.put("receiverSeq", receiverSeq);
		map.put("noteSeq", noteSeq);
		
		return session.selectOne("NoteMapper.readNote",map);
	}
	public void updateReadTime(Note note) {
		session.update("NoteMapper.updateReadTime",note);
	}
	public Note SenderNote(Integer senderSeq, Integer noteSeq) {
		Map<String,Integer> map = new HashMap<>();
		map.put("senderSeq", senderSeq);
		map.put("noteSeq", noteSeq);
		
		return session.selectOne("NoteMapper.senderNote",map);
	}
	/**
	 * 노트 삭제를 포시함
	 * @param note
	 * @param mode - 'S' or 'R'
	 */
	public void markAsDelete(Note note, String mode) {
		Map<String, Object> map = new HashMap<>();
		map.put("noteSeq", note.getSeq());
		map.put("mode", mode);
		
		session.update("NoteMapper.markAsDelete",map);
	}

}
