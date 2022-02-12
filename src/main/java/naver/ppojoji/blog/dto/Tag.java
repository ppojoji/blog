package naver.ppojoji.blog.dto;

public class Tag {
 private Integer seq;
 private String tagName;
 private Integer postSeq;
 
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
	@Override
	public String toString() {
		return "Tag [seq=" + seq + ", tagName=" + tagName + "]";
	}
	
}
