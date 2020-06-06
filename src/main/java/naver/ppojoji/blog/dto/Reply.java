package naver.ppojoji.blog.dto;

import java.util.Date;

public class Reply {
	private Integer seq;
	private String title;
	private String content; 
	private String writer;
	private String pwd;
	private Date replyTime;
	private Integer parent;
	
	public Reply() {
	}
	
	public Reply(Integer seq, String title, String content, String writer, String pwd,
			Integer parent) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.pwd = pwd;
		this.replyTime = new Date();
		this.parent = parent;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "Reply [seq=" + seq + ", title=" + title + ", content=" + content + ", writer=" + writer + ", pwd=" + pwd
				+ ", replyTime=" + replyTime + ", parent=" + parent + "]";
	}

	public boolean isSecret() {
		if(this.pwd != null) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
