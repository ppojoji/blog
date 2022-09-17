package naver.ppojoji.blog.dto;

import java.util.List;

public class Tag {
	private Integer seq;
	private String tagName;
	private Integer postSeq;
 
 	private List<Post> posts;
 	
 	private Integer tagCount;
	
 	public Integer getTagCount() {
		return tagCount;
	}
	public void setTagCount(Integer tagCount) {
		this.tagCount = tagCount;
	}
	
	public Integer getPostSeq() {
		return postSeq;
	}
	public void setPostSeq(Integer postSeq) {
		this.postSeq = postSeq;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "Tag [seq=" + seq + ", tagName=" + tagName + "]";
	}
	
}
