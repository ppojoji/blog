package naver.ppojoji.blog.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import naver.ppojoji.blog.Res;
import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.Note;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.NoteService;

@RestController
public class NoteController {
	@Autowired
	NoteService noteService;
	
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
	
	@GetMapping(value = "/notes/{type}")
	public List<Note> loadNote(HttpSession session, @PathVariable String type) {
		User loginUser = Util.getUser(session);
		List<Note> list = null;
		if(type.equals("S")) {
			 list = noteService.loadSendNote(loginUser.getSeq());
			
		}else if(type.equals("R")) {
			list = noteService.loadReceiverNote(loginUser.getSeq());
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
	@DeleteMapping(value = "/note/{noteSeq}/{mode}")
	public Note deleteNote(HttpSession session, @PathVariable Integer noteSeq,@PathVariable String mode){
		User loginUser = Util.getUser(session);
		
		Note note = noteService.deleteNote(mode, loginUser.getSeq(), noteSeq);
		return note;
	}
	
	
}
