function Naver () {
	
	var clientId = 'BcEIP2STKVMdRWiIR2cD'
	var callbackUrl = "http://" + window.location.hostname + ((location.port==""||location.port==undefined)?"":":" + location.port) + "/blog/oauth/naver/callback"
	
	console.log('네아로 모듈 작성중')
	console.log(callbackUrl)
	
	var naverLogin = new naver.LoginWithNaverId({
		clientId: clientId,
		callbackUrl: callbackUrl,
		/* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정합니다. */
	})
	naverLogin.init();
	var loginUrl = naverLogin.generateAuthorizeUrl()
	console.log(loginUrl)
	// 1) 로그인할때 
	return {
		prepareLogin() {
			$('#login-link').attr('href', loginUrl)
		},
		prepareCallback() {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					/* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
					console.log('>>> ', naverLogin.user)
					var email = naverLogin.user.getEmail();
					if( email == undefined || email == null) {
						alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
						/* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
						naverLogin.reprompt();
						return;
					}
					$.ajax({
						url : "/blog/oauth/naverLogin",
						method : "POST" , 
						data : {
							accessToken: naverLogin.accessToken.accessToken
						},
						success(res){
							console.log(res)
						}
					})
					// window.location.replace("http://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "/sample/main.html");
				} else {
					console.log("callback 처리에 실패하였습니다.");
				}
			})
		}
	}
}

window.Naver = new Naver()