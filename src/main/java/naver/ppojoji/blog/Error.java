package naver.ppojoji.blog;

public enum Error {

	NOT_EMPTY_CATE,
	DUP_CATE_NAME,
	EMPTY_CATE_NAME,
	NOT_FOUND, 
	BAD_REQUEST, 
	/**
	 * 글의 소유자가 아님
	 */
	NOT_OWNER_OF_POST,
	/**
	 * 관리자가 아님
	 */
	NOT_ADMIN
	
}
