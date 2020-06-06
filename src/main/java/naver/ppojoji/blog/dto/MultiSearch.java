package naver.ppojoji.blog.dto;

import naver.ppojoji.blog.BlogException;

public class MultiSearch {
	private String multiKeyword;	
	private boolean title;
	private boolean contents;
	private boolean writer;
	private Integer writerKeyword;
	
	public boolean isContents() {
		return contents;
	}
	public Integer getWriterKeyword() {
		return writerKeyword;
	}
	public void setWriterKeyword(Integer writerKeyword) {
		this.writerKeyword = writerKeyword;
	}
	public void setContents(boolean contents) {
		this.contents = contents;
	}
	public boolean isWriter() {
		return writer;
	}
	public void setWriter(boolean writer) {
		this.writer = writer;
	}
	public String getMultiKeyword() {
		return multiKeyword;
	}
	public void setMultiKeyword(String multiKeyword) {
		this.multiKeyword = multiKeyword;
	}
	public void setTitle(boolean title) {
		this.title = title;
		
	}
	public boolean isTitle() {
		return title;
	}
	
}
