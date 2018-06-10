$(document).ready(function (){
	
	$("#createTbutton").click(function() {
		var params = {
				"name" : $("#name").val(),
				"area" : $("#area").val(),
				"population" : $("#population").val()
			}
		console.log(params);
		$.ajax({
			type: "POST",
			url: "../WebProjekat/rest/territoryService/addTerritory",
			data: JSON.stringify(params),
			success: function(data) {
				if(data != null) {
					//redirect
				}
				else {
					alert("Territory already exists");
				}				  
		    },
		    dataType: "json",
		    contentType: "application/json"
		});
		
	});
	
});	
