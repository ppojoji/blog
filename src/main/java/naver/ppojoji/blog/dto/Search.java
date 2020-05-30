package naver.ppojoji.blog.dto;

import naver.ppojoji.blog.BlogException;

public class Search {
	private String searchType;
	private String keyword;	


	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		// title, content, reg_id 
		
		// TODO 입력 값 검서해야함
		if(searchType.equals("title") || searchType.equals("Contents") || 
				searchType.equals("writer")) {
			this.searchType = searchType;
		} else {
			// FIXME 이렇게 예외를 던지면 브라우저에서 500번 응답코드를 받게 됨(서버 잘못이 아닌데 내 잘못이 됨) 400번으로 응답코드가 넘어가게 해야 함
			throw new BlogException(400, "INVALID_SEARCH_TYPE");			
		}
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
