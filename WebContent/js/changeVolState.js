$(document).ready(function(){
	
	getAllVolunteers();
	
	$("#saveButton").click (function(){
		alert("kek yoma");
		$.ajax({
			url: "../WebProjekat/rest/volunteerService/updateVolunteer",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(controller.volunteers),
			success: function() {
				window.location.reload();
			}
		});
		
		
		
		
		
		
	});
	
});

var controller = {
	volunteers: []
};

function getAllVolunteers() {
	$.get("../WebProjekat/rest/volunteerService/getAllVolunteers", function(volunteers){
		controller.volunteers = volunteers;
		$.each(volunteers, function (index, volunteer) {
			addVolinteerToTable(index, volunteer);
		});
    });
}


function addVolinteerToTable(index, volunteer) {
	$('#volunteerTable').append(
		$('<tr>').append(
			$('<td>', {
				text : volunteer.username
			}),
			$('<td>', {
				text : volunteer.email
			}),
			$('<td>', {
				text : volunteer.firstName
			}),
			$('<td>', {
				text : volunteer.lastName
			}),
		
			$('<td>', {
				text : volunteer.profilePicture
			}),

			$('<td>', {
				text : volunteer.phoneNumber
			}),
			
			$('<td id="terr' + index + '">'),
			
			$('<td>').append(
				$('<select onchange="setVolState(\'' + volunteer.username + '\', this.value)">').append(
					$('<option>', {
						 value: "ACTIVE",
						 text: "ACTIVE",
						 selected: volunteer.userStatus === 'ACTIVE'
						 
					}),
					$('<option>', {
						 value: "BLOCK",
						 text: "BLOCK",
						 selected: volunteer.userStatus === 'BLOCK'
					})
				
			))
		)
	)
	
	// set territory field to territory name insted of territoryId
	$.ajax({
		url: "../WebProjekat/rest/territoryService/getOne",
		type: "GET",
		data: {"territoryId" : volunteer.territoryId},
		success: function(territory) {
			$('#terr' + index).append(
				$('<p>', { 
					text : territory.name
				})
			);
		}
	});
	
	
	
}


function setVolState(volunteerUsername, volState) {
	for(var i = 0; i < controller.volunteers.length; i++) {
		if(controller.volunteers[i].username === volunteerUsername) {
			controller.volunteers[i].userStatus = volState;
		}
	}
}

