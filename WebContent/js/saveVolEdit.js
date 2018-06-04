$(document).ready(function() {
		
	loadDataToTextFields();
	loadTerritories();
	
	
	$("#saveEditVolButton").click(function() {
		
		var formData = new FormData();
		formData.append('newPassword', $("#newPassword").val());
		formData.append('oldPassword', $("#oldPassword").val());
		formData.append('email', $("#email").val());
		formData.append('firstName', $("#firstName").val());
		formData.append('lastName', $("#lastName").val());
		formData.append('image', document.querySelector('input[type=file]').files[0]);
		formData.append('phoneNumber', $("#phoneNumber").val());
		formData.append('territory', $("#territories option:selected").val());
		
		$.ajax({
	      url: "../WebProjekat/rest/userService/updateUser",
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

function loadDataToTextFields() {
	$("#email").val(sessionStorage.email);
	$("#firstName").val(sessionStorage.firstName);
	$("#lastName").val(sessionStorage.lastName);
	$("#phoneNumber").val(sessionStorage.phoneNumber);
}

function loadTerritories() {
	$.get("../WebProjekat/rest/territoryService/getAll", function(territories){
		$.each(territories, function (index, territory) {
		    $('#territories').append($('<option>', { 
		        value: territory.id,
		        text : territory.name,
		        selected: sessionStorage.territory === territory.id
		    }));
		});
    });
}