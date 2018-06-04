$(document).ready(function(){
	
	getAllEmergencies();
	
	$("#saveButton").click (function() {
		console.log(controller.emSituations);
		
		$.ajax({
			url: "../WebProjekat/rest/emergencyService/updateEmergency",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(controller.emSituations),
			success: function() {
				window.location.reload();
			}
		});
	});
	

	
});

var controller = {
	emSituations : []
};

function getAllEmergencies() {
	$.get("../WebProjekat/rest/emergencyService/getAllEmergencies", function(emergencies){
		controller.emSituations = emergencies;
		$.each(emergencies, function (index, emergency) {
		    addEmergencyToTable(index, emergency);
		});
    });
}

function addEmergencyToTable(index, emergency) {
	
	$('#emirgencyTable').append(
		$('<tr>').append(
			$('<td>', {
				text : emergency.locationName
			}),
			$('<td>', {
				text : emergency.municipalitie
			}),
			$('<td>', {
				text : emergency.description
			}),
			$('<td>', {
				text : emergency.dateTime
			}),
			$('<td>', {
				text : emergency.gMapLoc
			}),
			$('<td id="terr' + index + '">'),
			$('<td>', {
				text : emergency.emergencyType
			}),
			$('<td>', {
				text : emergency.picture
			}),
			$('<td>').append(
				$('<select onchange="setEmState(\'' + emergency.id + '\', this.value)">').append(
					$('<option>', {
						 value: "ACTIVE",
						 text: "ACTIVE",
						 selected: emergency.status === 'ACTIVE'
					}),
					$('<option>', {
						 value: "ARCHIVE",
						 text: "ARCHIVE",
						 selected: emergency.status === 'ARCHIVE'
					})
				
			)),
			$('<select id="cb' + index + '" onchange="setEmVolunteer(\'' + emergency.id + '\', this.value)">')
		)
	)
	
	// set territory field to territory name insted of territoryId
	$.ajax({
		url: "../WebProjekat/rest/territoryService/getOne",
		type: "GET",
		data: {"territoryId" : emergency.territory},
		success: function(territory) {
			$('#terr' + index).append(
				$('<p>', { 
					text : territory.name
				})
			);
		}
	});
	
	// load volunteers to the select field
	$.ajax({
		url: "../WebProjekat/rest/volunteerService/getVolWithTerritoryId",
		type: "GET",
		data: {"territoryId" : emergency.territory},
		success: function(volunteers) {
			
			if(emergency.volunteer == undefined || emergency.volunteer == "")
				emergency.volunteer = volunteers[0].username;
			
			$.each(volunteers, function (i, volunteer) {
				$('#cb' + index).append(
					$('<option>', { 
						value: volunteer.username,
						text : volunteer.username,
						selected: emergency.volunteer === volunteer.id
					})
				);
			});
		}
	});
}

function setEmState(emirgencyId, emergencyState) {
	for(var i = 0; i < controller.emSituations.length; i++) {
		if(controller.emSituations[i].id === emirgencyId) {
			controller.emSituations[i].emergencyState = emergencyState;
		}
	}
}

function setEmVolunteer(emirgencyId, volunteerId) {
	for(var i = 0; i < controller.emSituations.length; i++) {
		if(controller.emSituations[i].id === emirgencyId) {
			controller.emSituations[i].volunteer = volunteerId;
		}
	}
}