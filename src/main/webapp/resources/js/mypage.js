$(document).ready(function(){
	
	$.ajax({
		url: '/blog/api/myinfo',
		method: 'GET',
		success: function(res) {
			console.log(res)
		}
	})
})