package naver.ppojoji.blog.dto;
/**
 * 블로그에서 작성하는 글을 나타냅니다.
 * 
 * @author HA
 *
 */

import java.util.Date;
import java.util.List;

public class Post {
	private Integer seq; // 테이블의 PK를 담습니다.
	private String title;
	private String contents;
	private Date creationDate  ;
	private int viewCount;
	private String viewPass;
	private User writer;
	private List<LocalUpFile> upfiles;
	private boolean open;
	
	private Integer replyCount;
	private Integer upfileCount;
	
	private Category category;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Post() {
	}
	
	public Post(Integer seq, String title, String contents, Date creationDate, int viewCount) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.creationDate = creationDate;
		this.viewCount = viewCount;
	}

	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
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
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	public String getViewPass() {
		return viewPass;
	}

	public void setViewPass(String viewPass) {
		this.viewPass = viewPass;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getUpfileCount() {
		return upfileCount;
	}

	public void setUpfileCount(Integer upfileCount) {
		this.upfileCount = upfileCount;
	}

	@Override
	public String toString() {
		return "Post [seq=" + seq + ", title=" + title + ", contents=" + contents + ", creationDate=" + creationDate
				+ ", viewCount=" + viewCount + ", writer=" + writer + "]";
	}

	public List<LocalUpFile> getUpFiles() {
		return this.upfiles;
	}
	public void setUpFiles(List<LocalUpFile> upfiles) {
		this.upfiles = upfiles;
	}
	
}
