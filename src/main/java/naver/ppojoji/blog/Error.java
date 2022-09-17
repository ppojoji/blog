package naver.ppojoji.blog;

public enum Error {

	NOT_EMPTY_CATE,
	DUP_CATE_NAME,
	EMPTY_CATE_NAME,
	NOT_FOUND, 
	BAD_REQUEST, 
	LOGIN_REQUIRED,
	/**
	 * 글의 소유자가 아님
	 */
	NOT_OWNER_OF_POST,
	/**
	 * 관리자가 아님
	 */
	NOT_ADMIN,
	/**
	 * 사용정지에 걸린 사용자
	 */
	BANNED_USER,
	/**
	 * 로그인 실패
	 */
	LOGIN_FAILED
	
}
