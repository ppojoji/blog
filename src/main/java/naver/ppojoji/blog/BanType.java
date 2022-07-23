package naver.ppojoji.blog;

public enum BanType {

	AD,
	PN,
	AB,
	GM,
	ET;
	
	public static BanType toBanType(String value) {
		if ("all".equals(value)) {
			return null;
		} else {
			return BanType.valueOf(value);
		}
	}
}
