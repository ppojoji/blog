package naver.ppojoji.blog.dao;

import java.util.Date;
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
	public List<Note> loadSendNote(Integer sender, Integer lastNoteSeq, Integer size) {
//		return session.selectList("NoteMapper.loadSendNote",sender);
		Map map = new HashMap<>();
		map.put("sender", sender);
		map.put("lastNoteSeq", lastNoteSeq);
		map.put("size", size);
		
		return session.selectList("NoteMapper.loadSendNote",map);
	}
	public List<Note> loadReceiverNote(Integer receiver, Integer lastNoteSeq, Integer size) {
		Map map = new HashMap<>();
		map.put("receiver", receiver);
		map.put("lastNoteSeq", lastNoteSeq);
		map.put("size", size);
		
		return session.selectList("NoteMapper.loadReceiverNote",map);
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
	public void markAsDelete(Note note) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("noteSeq", note.getSeq());
//		map.put("mode", mode);
		
		session.update("NoteMapper.markAsDelete",note);
	}

	public void deleteNote(Integer noteSeq) {
		session.delete("NoteMapper.deleteNote",noteSeq);
	}
	public List<Note> queryMessage(Integer userSeq, Date current, Integer maxSeq) {
		Map<String,Object> map = new HashMap<>();
		map.put("userSeq", userSeq);
		map.put("current", current);
		map.put("maxSeq", maxSeq);
		
		return session.selectList("NoteMapper.queryMessage", map);
	}
	public void updateOriginNote(Integer noteSeq) {
		session.update("NoteMapper.updateOriginNote",noteSeq);
	}
	public int countReplyNote(Integer noteSeq) {
		return session.selectOne("NoteMapper.countReplyNote",noteSeq);
	}
	public List<Note> noteHistory(Integer origin_note_Seq) {
		Map<String,Integer> map = new HashMap<>();
		map.put("origin_note_Seq", origin_note_Seq);
		
		return session.selectList("NoteMapper.noteHistory",map);
	}
}
