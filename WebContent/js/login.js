$(document).ready(function (){
	
	$("#loginButton").click(function() {
		var formData  = new FormData();
		formData.append("username",$("#username").val());
		formData.append("password",$("#password").val());
		$.ajax({
			  type: "POST",
			  url: "../WebProjekat/rest/userService/login",
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
						  
						  window.location.replace("admin.html");
						  
					  }
					  else if(sessionStorage.userType === "VOLUNTEER") {
						  if(data.userStatus !== "BLOCK")
							  window.location.replace("volunteerPage.html");
						  else
							  alert("YOUR ACCOUNT HAS BEEN BLOCKED BY AN ADMIN :(");
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
