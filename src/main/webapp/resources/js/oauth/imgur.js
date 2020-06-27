function ImgurAPI() {
	var authUrl = 'https://api.imgur.com/oauth2/authorize?client_id=909952ed6b00cc9&response_type=token'
	var endpoint = 'https://api.imgur.com/3' 
	var $wrapper
	var tokens 
	// var upImages = [] 
	
	function loadTokens(callback) {
		$.ajax({
			url : "/blog/oauth/imgur/getToken",
			method : "GET" , 
			success(res){
				callback(res)
			} 
		})
		
	}
	function renderAuthRequired() {
	    $wrapper.find('.btn-upload').hide() 
	    $wrapper.find('.modal-body').html(`<p>이미지를 불러오기 위해서는 권한이 필요합니다</p><p><a target="_blank" href="${authUrl}">imgur 권한 페이지로 이동</a>`)
	}
	function renderUploadPage() { 
		$wrapper.find('.btn-upload').show()
		$wrapper.find('.modal-body').html(`<input type="file" class="upfile">`) 
	} 
	function doUpload() {
		var form = new FormData();
		var fs = $wrapper.find(".upfile")[0].files
		form.append('image', fs[0]);
		form.append('name', fs[0].name)
		$.ajax({
		    "url": "https://api.imgur.com/3/image",
		    "method": "POST",
		    "timeout": 0,
		    "headers": {
		        "Authorization": "Bearer " + tokens.accessToken
		    },
		    "processData": false,
		    "contentType": false,
		    "data": form,
		    success(res) {
		    	var url = res.data.link
		    	var name = ' '
		    	$('#content').summernote('insertImage', url, name);
		        console.log(res)
		        closeDialog()
		    }
		})
	}
	function closeDialog() {
		$wrapper.modal('hide')
	}
	$(document).ready(() => {
	    $wrapper = $('#imgur-upload')
	    
	    $wrapper.find('.btn-upload').click(e => {
	        doUpload(res => {
	        	console.log(res)
	        	/*
	            $('#content').summernote('insertImage', 
	            		res.data.link, 
	            		res.data.name)
	            */
	            // closeDialog()
	        })
	    })
	})
	return {
	    openDialog() {
	        $wrapper.modal('show')
	        
	        loadTokens((res) => {
	            console.log(res) 
	            if (res.success) {
	                tokens = res.token 
	                renderUploadPage()
	            } else {
	                renderAuthRequired()
	            }
	        })
	    }
	}
}

window.imgur = new ImgurAPI()