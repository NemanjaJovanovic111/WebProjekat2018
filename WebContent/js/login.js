$(document).ready(function (){
	
	$("#loginButton").click(function() {
		var formData  = new FormData();
		formData.append("username",$("#username").val());
		formData.append("password",$("#password").val());
		$.ajax({
			  type: "POST",
			  url: "../WebProjekat/rest/loginService/login",
			  data: JSON.stringify({
				  "username" : $("#username").val(),
				  "password" : $("#password").val()
			  }),
			  success: function(data) {
				  if(data != null) {
				      sessionStorage.username = data.username;
					  sessionStorage.firstName = data.firstName;
					  sessionStorage.lastName = data.lastName;
					  sessionStorage.userType = data.userType;
					  sessionStorage.phoneNumber = data.phoneNumber;
					  sessionStorage.email = data.email;
					  sessionStorage.profilePicture = data.profilePicture;
					  sessionStorage.territory = data.territory;
					  
					  if(sessionStorage.userType === "ADMIN") {
						  
						  window.location.replace("createTerritory.html");

						  
					  }
					  else if(sessionStorage.userType === "VOLUNTEER") {
						  
						  window.location.replace("emergency.html");
					  }
				  }
				  else {
					  alert("Invalid username/password combination!");
				  }				  
		      },
		      dataType: "json",
		      contentType: "application/json"
			});
		
	});
	
});	
