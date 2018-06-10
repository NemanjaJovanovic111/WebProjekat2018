$(document).ready(function () {
	loadEmergencyData();
	loadComments();
	
	$("#commentButton").click(function () {
		createComment();
	});
});


function initMap() {
	// Novi Sad , Kisacka 45.267136, 19.833549
	// FTN 45.246101, 19.851697

	//var lat = parseFloat(sessionStorage.gMapLoc.split(";")[0]);
	//var lng = parseFloat(sessionStorage.gMapLoc.split(";")[1]);
	var myLatLng = { lat: 45.246101, lng: 19.851697 };
	//var myLatLng = {lat: lat, lng: lng};


	map = new google.maps.Map(document.getElementById('map'), {
		zoom: 16,
		center: myLatLng
	});

	marker = new google.maps.Marker({
		position: myLatLng,
		map: map,
		title: 'Hello World!'
	});
}

function loadEmergencyData() {
	if (sessionStorage.emergencyState === "ARCHIVE") {
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
			"emergencyId": sessionStorage.id
		},
		success: function (comments) {
			fillTable(comments);
		}
	});
}

function createComment() {

	$.ajax({
		type: "POST",
		url: "../WebProjekat/rest/commentService/create",
		data: JSON.stringify({
			"textComm": $("#commentText").val(),
			"username": sessionStorage.username,
			"emergencyId": sessionStorage.id
		}),
		success: function (data) {
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
			"username": username
		},
		success: function (user) {
			$("#commPic" + index).attr("src", user.profilePicture);
		}
	});
}