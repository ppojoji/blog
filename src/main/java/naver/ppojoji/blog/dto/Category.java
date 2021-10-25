package naver.ppojoji.blog.dto;

public class Category {
	private Integer seq;
	private String name;
	private Integer post_cnt;
	private Integer ordernum;
	private String useYn;
	
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPost_cnt() {
		return post_cnt;
	}
	public void setPost_cnt(Integer post_cnt) {
		this.post_cnt = post_cnt;
	} 
}
