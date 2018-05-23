

$(document).ready(function(){
	
	$("#registerButton").click(function() {
		
		if(!passwordCheck) 
			return;
		
		var formData = new FormData();
		formData.append('username', $("#username").val());
		formData.append('password', $("#password").val());
		formData.append('email', $("#email").val());
		formData.append('name', $("#name").val());
		formData.append('surname', $("#surname").val());
		formData.append('phone_number', $("#phone_number").val());
		
		//formData.append('role', $("input[name=role]:checked").val()); ovako ce nesto biti za territory polje
		//formData.append('uploadfile', document.querySelector('input[type=file]').files[0]); namikeri da slanje fajla radi, sa jquery
		
		
		$.ajax({
	      url: "../WebProjekat/rest/registerService/register",
	      type: 'POST',
	      xhr: function() {  // Custom XMLHttpRequest
	        var myXhr = $.ajaxSettings.xhr();
	        return myXhr;
	      },
	      // beforeSend: beforeSendHandler,
	      success: function(data) {
	    	  
	    	  console.log("SERVER RESPONSE ARRIVED!");
	    	  
//	    	  if(data == 'u') {
//	    		  toastr.info("Username is already in use!");
//	    	  }
//	    	  else if(data == 'e') {
//	    		  toastr.info("Already exists email for selected role!");
//	    	  }
//	    	  else {
//	    		  toastr.info("Successful registration! Log in, please.");
//	    		  window.location.href = "index.html";
//	    	  }
	      },
	      // Form data
	      data: formData,
	      //Options to tell jQuery not to process data or worry about content-type.
	      cache: false,
	      contentType: false,
	      processData: false
	    });
	});
	
});

function passwordCheck() {
	var password = $("#password").val();
	var confPassword = $("conf_password").val();
	
	if(password !== confPassword) {
		alert("'Password and 'Confirm Password' fields must have the same values");
		return false;
	}
	return true;
	
}