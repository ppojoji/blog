package naver.ppojoji.blog.dto;

public class Message {
	private Long seq;
	private String sender; 
	private Integer receiver;
	private String content; 
	private String sendtime; 
	private String readtime;
	
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Integer getReceiver() {
		return receiver;
	}
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getReadtime() {
		return readtime;
	}
	public void setReadtime(String readtime) {
		this.readtime = readtime;
	}
	@Override
	public String toString() {
		return "Message [seq=" + seq + ", sender=" + sender + ", receiver=" + receiver + ", content=" + content
				+ ", sendtime=" + sendtime + ", readtime=" + readtime + "]";
	}
}
