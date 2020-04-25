
function timeDiff(millis, curMillis) {
	var diffMillis = curMillis - millis // 밀리세컨드
	var sec = diffMillis / 1000 // 초단위
	var min = parseInt(sec / 60) // 분단위
	if(min === 0) {
		return sec + '초전'
	}
	
	var hour = parseInt(min / 60) // 시간
	if(hour === 0) {
		return min + '분전'
	}
	var days = parseInt(hour / 24) // 일전~
	if(days === 0) {
		return hour + '시간전'
	}
	
	var month = parseInt(days / 30) // ~달전
	if(month === 0) {
		return days + '일전'
	}
	return month + '달전'
}