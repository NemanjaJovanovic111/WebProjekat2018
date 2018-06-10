$(document).ready(function () {
	getMyEmergencies();
});

function getMyEmergencies() {

	$.ajax({
		url: "../WebProjekat/rest/emergencyService/getByVolUsername",
		type: "GET",
		data: { "volUsername": sessionStorage.username },
		success: function (emergencies) {
			$.each(emergencies, function (index, emergency) {
				setEmergencyTerretoryName(emergency);	
			});
			
		}
	});

}

function fillTable(emergencies) {
	$.each(emergencies, function(index, emergency) {
		$('#myEmergencySituationTable tbody').append(
			$('<tr>').append(
				$('<td>', {
					text: emergency.locationName
				}),
				$('<td>', {
					text: emergency.municipalitie
				}),
				$('<td>', {
					text: emergency.description
				}),
				$('<td>', {
					text: emergency.dateTime
				}),
				$('<td>', {
					text: emergency.gMapLoc
				}),
				$('<td>', {
					text: emergency.territory.name
				}),
				$('<td>', {
					text: emergency.emergencyType
				})
			)
		);
	});
}

function setEmergencyTerretoryName(emergency) {
	$.ajax({
		url: "rest/territoryService/getOne/",
		type: "GET",
		data: { "territoryId": emergency.territory },
		success: function (territory) {
			emergency.territory = territory;
			fillTable([emergency])
		}
	});
}