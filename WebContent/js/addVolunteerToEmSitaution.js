$(document).ready(function(){
	
	getAllEmergencies();
	
});

function getAllEmergencies() {
	$.get("../WebProjekat/rest/emergencyService/getAllEmergencies", function(emergencies){
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
				$('<select>').append(
					$('<option>', {
						 value: "ACTIVE",
						 text: "ACTIVE"
					}),
					$('<option>', {
						 value: "ARCHIVE",
						 text: "ARCHIVE"
					})
				
			)),
			$('<select id="cb' + index + '">')
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
			$.each(volunteers, function (i, volunteer) {
				console.log(volunteer);
				$('#cb' + index).append(
					$('<option>', { 
						text : volunteer.username
					})
				);
			});
		}
	});
}