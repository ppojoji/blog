package naver.ppojoji.blog;

public class BlogException extends RuntimeException {
	private int responseCode;
	
	public BlogException(int responseCode, String errorCode) {
		super(errorCode);
		this.responseCode = responseCode;
	}

	public int getResponseCode() {
		return responseCode;
	}

}
