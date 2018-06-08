$(document).ready(function() {
	loadEmergencyData();
	loadComments();
	
	$("#commentButton").click(function() {
		createComment();
	});
});


function loadEmergencyData() {
	console.log(sessionStorage.emergencyState)
	if(sessionStorage.emergencyState === "ARCHIVE") {
		$('#commentText').attr("disabled", true);
	}
	$("#image").attr("src", sessionStorage.picture);
	$("#locationName").text(sessionStorage.locationName);
	$("#municipality").text(sessionStorage.municipalitie);
	$("#description").text(sessionStorage.description);
	$("#dateTime").text(sessionStorage.dateTime);
	$("#gMapLoc").text(sessionStorage.gMapLoc);
	$("#territory").text(sessionStorage.territory);
	$("#type").text(sessionStorage.emergencyType);
	$("#state").text(sessionStorage.emergencyState);
	$("#volunteer").text(sessionStorage.volunteer);
}

function loadComments() {
	$.ajax({
		url: "../WebProjekat/rest/commentService/getByEmergencyId",
		type: "GET",
		contentType: "application/json",
		data: {
			"emergencyId" : sessionStorage.id
		},
		success: function(comments) {
			fillTable(comments);
		}
	});
}

function createComment() {
	
	$.ajax({
		  type: "POST",
		  url: "../WebProjekat/rest/commentService/create",
		  data: JSON.stringify({
			  "textComm" : $("#commentText").val(),
			  "username" : sessionStorage.username,
			  "emergencyId" : sessionStorage.id
		  }),
		  success: function(data) {
			  loadComments();
			  $("#commentText").val("");
	      },
	      dataType: "json",
	      contentType: "application/json"
	});
}


function fillTable(comments) {
	
	clearTable();
	
	$.each(comments, function (index, comment) {
	    $('#comments').append(
	    	$('<tr>').append(
	    		$('<td>').append(
	    			$('<table>').append(
	    	    		$('<tr>').append(
	    	    			$('<td>').append(
	    	    				$('<image id="commPic' + index + '" height="100px" width="100px">')
	    	    			)
	    	    		),
	    	    		$('<tr>').append(
		    	    		$('<td>').append(
		    	    			$('<p>' + comment.username + '</p>')
		    	    		)
		    	    	),
		    	    	$('<tr>').append(
			    	    	$('<td>').append(
			    	    		$('<p>' + comment.dateTime + '</p>')
			    	    	)
			    	    )
	    	    	)
	    	    ),
	    		$('<td>').append(
	    			$('<p>' + comment.textComm + '</p>')
	    		)	
	    	)
	    );
	    getUserPicture(comment.username, index);
	});
	
}

function clearTable() {
	$("#comments").empty();
}

function getUserPicture(username, index) {
	$.ajax({
		url: "../WebProjekat/rest/userService/getOne",
		type: "GET",
		contentType: "application/json",
		data: {
			"username" : username
		},
		success: function(user) {
			$("#commPic" + index).attr("src", user.profilePicture);
		}
	});
}