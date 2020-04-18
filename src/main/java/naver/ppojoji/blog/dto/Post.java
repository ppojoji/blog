package naver.ppojoji.blog.dto;
/**
 * 블로그에서 작성하는 글을 나타냅니다.
 * 
 * @author HA
 *
 */

import java.util.Date;

public class Post {
	private Long seq; // 테이블의 PK를 담습니다.
	private String title;
	private String contents;
	private Date creationDate  ;
	private int viewCount;
	
	private User writer;
	
	public Post() {
	}
	
	public Post(Long seq, String title, String contents, Date creationDate, int viewCount) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.creationDate = creationDate;
		this.viewCount = viewCount;
	}

	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Post [seq=" + seq + ", title=" + title + ", contents=" + contents + ", creationDate=" + creationDate
				+ ", viewCount=" + viewCount + ", writer=" + writer + "]";
	}
	
	
	
}
