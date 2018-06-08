$(document).ready(function(){
	
	loadTerritories();
	
	$("#registerButton").click(function() {
		
		if(!passwordCheck) 
			return;
		
		var formData = new FormData();
		formData.append('username', $("#username").val());
		formData.append('password', $("#password").val());
		formData.append('email', $("#email").val());
		formData.append('firstName', $("#name").val());
		formData.append('lastName', $("#surname").val());
		formData.append('phoneNumber', $("#phone_number").val());
		formData.append('territory', $("#territories option:selected").val());
		formData.append('image', document.querySelector('input[type=file]').files[0]);
		
		$.ajax({
	      url: "../WebProjekat/rest/registerService/register",
	      type: 'POST',
	      xhr: function() {  // Custom XMLHttpRequest
	        var myXhr = $.ajaxSettings.xhr();
	        return myXhr;
	      },
	      success: function(data) {
	    	  console.log("SERVER RESPONSE ARRIVED!");
	      },
	      data: formData,
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

function loadTerritories() {
	$.get("../WebProjekat/rest/territoryService/getAll", function(territories){
		$.each(territories, function (index, territory) {
		    $('#territories').append($('<option>', { 
		        value: territory.id,
		        text : territory.name
		    }));
		});
    });
}