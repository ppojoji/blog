package naver.ppojoji.blog.dto;

public class LocalUpFile {
	private Integer Seq;
	private String OriginalName;
	private String GenName; 
	private Integer FileSize; 
	private String ContentType; 
	private Integer Post; 
	private String Origin;
	public Integer getSeq() {
		return Seq;
	}
	public void setSeq(Integer seq) {
		Seq = seq;
	}
	public String getOriginalName() {
		return OriginalName;
	}
	public void setOriginalName(String originalName) {
		OriginalName = originalName;
	}
	public String getGenName() {
		return GenName;
	}
	public void setGenName(String genName) {
		GenName = genName;
	}
	public Integer getFileSize() {
		return FileSize;
	}
	public void setFileSize(Integer fileSize) {
		FileSize = fileSize;
	}
	public String getContentType() {
		return ContentType;
	}
	public void setContentType(String contentType) {
		ContentType = contentType;
	}
	public Integer getPost() {
		return Post;
	}
	public void setPost(Integer post) {
		Post = post;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	@Override
	public String toString() {
		return "LocalUpFile [Seq=" + Seq + ", OriginalName=" + OriginalName + ", GenName=" + GenName + ", FileSize="
				+ FileSize + ", ContentType=" + ContentType + ", Post=" + Post + ", Origin=" + Origin + "]";
	} 
	
}
