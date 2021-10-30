package naver.ppojoji.blog.dto;

import naver.ppojoji.blog.BoardType;
import naver.ppojoji.blog.YesNo;

public class Category {
	private Integer seq;
	private String name;
	private Integer post_cnt;
	private Integer ordernum;
	private YesNo useYn;
	private YesNo replyYN;
	private BoardType type;
	
	public Category() {
		this.useYn = YesNo.Y;
		this.replyYN= YesNo.Y;
		this.type = BoardType.NM;
	}
	
	public BoardType getType() {
		return type;
	}
	public void setType(BoardType type) {
		this.type = type;
	}
	public YesNo getReplyYN() {
		return replyYN;
	}
	public void setReplyYN(YesNo replyYN) {
		this.replyYN = replyYN;
	}
	public YesNo getUseYn() {
		return useYn;
	}
	public void setUseYn(YesNo useYn) {
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
