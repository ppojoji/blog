package naver.ppojoji.blog.dto;

import java.util.Date;

public class Note {
	private Integer seq;
	private Integer sender;
	private Integer receiver;
	private Date readTime;
	private Date sendTime;
	private String sender_Delete;
	private String receiver_Delete;
	private String content;
	private String senderId;
	private String receiverId;
	
	
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getSender() {
		return sender;
	}
	public void setSender(Integer sender) {
		this.sender = sender;
	}
	public Integer getReceiver() {
		return receiver;
	}
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getSender_Delete() {
		return sender_Delete;
	}
	public void setSender_Delete(String sender_Delete) {
		this.sender_Delete = sender_Delete;
	}
	public String getReceiver_Delete() {
		return receiver_Delete;
	}
	public void setReceiver_Delete(String receiver_Delete) {
		this.receiver_Delete = receiver_Delete;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Note [seq=" + seq + ", sender=" + sender + ", receiver=" + receiver + ", readTime=" + readTime
				+ ", sendTime=" + sendTime + ", sender_Delete=" + sender_Delete + ", receiver_Delete=" + receiver_Delete
				+ ", content=" + content + ", senderId=" + senderId + "]";
	}
	
	
}
