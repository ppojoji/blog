package naver.ppojoji.blog.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.Res;
import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.Note;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.NoteService;
import naver.ppojoji.blog.service.UserService;

@RestController
public class NoteController {
	@Autowired
	NoteService noteService;
	
	@Autowired
	UserService userService;
	/**
	 * 새로운 쪽지 작성
	 * @param note
	 * @return
	 */
	@PostMapping("/note")
	// @ResponseBody
	private Object CreateNote(@RequestBody Note note) {
		Integer senderSeq = (Integer) note.getSender();
		Integer receiverSeq = (Integer) note.getReceiver();
		String content = (String) note.getContent();
		
		System.out.println(senderSeq + " > " + receiverSeq);
		System.out.println(content);
		
		Note insertedNote = noteService.createNote(note);
		
		return Res.success("note", insertedNote);
	}
	@PostMapping("/note/reply")
	// @ResponseBody
	private Object ReplyNote(HttpSession session , @RequestBody Note note) {
		
		User loginUser = Util.getUser(session);
		
		Integer senderSeq = (Integer) note.getSender();
		Integer receiverSeq = (Integer) note.getReceiver();
		String content = (String) note.getContent();
		Integer prevNote = note.getPrev_note();
		
		System.out.println(senderSeq + " > " + receiverSeq);
		System.out.println(content);
		
		Note insertedReplyNote = noteService.replyNote(loginUser,note);
		
		
		return Res.success("note", insertedReplyNote);
	}
	/**
	 * 쪽지 목록 조회(보낸 쪽지, 받은쪽지)
	 * url: http://.../notes/S?last=98
	 * @param session
	 * @param type - "S" or "R"
	 * @return
	 */
	@GetMapping(value = "/notes/{type}")
	public List<Note> loadNote(HttpSession session,
			@PathVariable String type,
			@RequestParam(required = false, defaultValue = "100000000") Integer last,
			@RequestParam(required = false, defaultValue = "5") Integer size) {
		User loginUser = Util.getUser(session);
		List<Note> list = null;
		if(type.equals("S")) {
			list = noteService.loadSendNote(loginUser.getSeq(), last, size);			
		} else if(type.equals("R")) {
			list = noteService.loadReceiverNote(loginUser.getSeq());
		} else {
			throw new BlogException(401, "INVALIDATE_TYPE");
		}
		
		return list;
	}
	/**
	 * 받은 쪽지 상세 내용 - 안 읽었으면 읽은 시간을 갱신함
	 * @param session
	 * @param noteSeq
	 * @return
	 */
	@PutMapping(value = "/note/{noteSeq}")
	public Note readNote(HttpSession session, @PathVariable Integer noteSeq){
		User loginUser = Util.getUser(session);
		
		Note note = noteService.readNote(loginUser.getSeq(),noteSeq);
		return note;
	}
	/**
	 * 보낸 쪽지 상세 내용
	 * @param session
	 * @param noteSeq
	 * @return
	 */
	@GetMapping(value = "/note/{noteSeq}")
	public Note readSentNote(HttpSession session, @PathVariable Integer noteSeq){
		User loginUser = Util.getUser(session);
		
		Note note = noteService.SenderNote(loginUser.getSeq(),noteSeq);
		return note;
	}
	/**
	 * 쪽지 삭제 처리
	 * @param session
	 * @param noteSeq
	 * @param mode
	 * @return
	 */
	@DeleteMapping(value = "/note/{noteSeq}/{mode}")
	public Note deleteNote(HttpSession session, @PathVariable Integer noteSeq,@PathVariable String mode){
		User loginUser = Util.getUser(session);
		
		Note note = noteService.deleteNote(mode, loginUser.getSeq(), noteSeq);
		return note;
	}
	@GetMapping(value = "/notes/new/{maxSeq}")
	public List<Note> QueryMessage(HttpSession session, @PathVariable Integer maxSeq) {
		User loginUser = Util.getUser(session);
		
		List<Note> list = null;

		if(loginUser != null) {
			list = noteService.queryMessage(loginUser.getSeq(),maxSeq);
			userService.UpdateReadNote(loginUser.getSeq(), maxSeq);
		}else {
			list = new ArrayList<>();
		}
		
		return list;
	}
	@GetMapping(value = "/note/history/{noteSeq}/{mode}")
	public List<Note> NoteHistory(HttpSession session,@PathVariable Integer noteSeq,@PathVariable String mode) {
		User loginUser = Util.getUser(session);
		
		List<Note> note = noteService.noteHistory(loginUser.getSeq(),noteSeq,mode);
			
		return note;
	}
}
