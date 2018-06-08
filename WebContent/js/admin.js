$(document).ready(function() {
	getActiveEmergencies();
});

function getActiveEmergencies() {
	$.get("../WebProjekat/rest/emergencyService/getActive", function(emergencies) {
		fillTable(emergencies);
    });
}


function fillTable(emergencies) {
	
	clearTable();
	$.each(emergencies, function (index, emergency) {
		
	    $('#emergencies').append(
	    	$('<tr>').append(
	    		$('<td>').append(
	    			$('<input type="button" value="View" onclick="viewEmergency(\'' + emergency.id + '\',\'' + 
	    					emergency.locationName + '\',\'' + emergency.municipalitie + '\',\'' + emergency.description + 
	    					'\',\'' + emergency.dateTime + '\',\'' + emergency.gMapLoc + '\',\'' + emergency.territory + 
	    					'\',\'' + emergency.emergencyType + '\',\'' + emergency.picture + '\',\'' + emergency.emergencyState + 
	    					'\',\'' + emergency.volunteer + '\')"/>')
	    		),
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
	    		$('<td id="terr' + index + '">'),
	    		$('<td>', {
	    			text: emergency.emergencyType
	    		}),
	    		$('<td>', {
	    			text: emergency.volunteer
	    		})
	    	)
	    );
	    
		var territoryName = "";
		$.ajax({
			url: "rest/territoryService/getOne/",
			type: "GET",
			data: {"territoryId" : emergency.territory},
			success : function (territory) {
				 $("#terr" + index).text(territory.name);	
			}
		});
	});
}

function clearTable() {
	$("#emergencies").empty();
	$('#emergencies').append(
	    $('<tr>').append(
	    	$('<th>'),
	    	$('<td>Location Name</th>'),
	    	$('<td>Municipality</th>'),
	    	$('<td>Description</th>'),
	    	$('<td>DateTime</th>'),
	    	$('<td>gMapLoc</th>'),
	    	$('<td>Territory</th>'),
	    	$('<td>Type</th>'),
	    	$('<td>Volunteer</th>')
	    )
	)
}

function viewEmergency(emergencyId, locationName, municipalitie, description, dateTime, gMapLoc,
		territory, emergencyType, picture, emergencyState, volunteer) {
	sessionStorage.id = emergencyId;
	sessionStorage.locationName = locationName;
	sessionStorage.municipalitie = municipalitie;
	sessionStorage.description = description;
	sessionStorage.dateTime = dateTime;
	sessionStorage.gMapLoc = gMapLoc;
	sessionStorage.territory = territory;
	sessionStorage.emergencyType = emergencyType;
	sessionStorage.picture = picture;
	sessionStorage.emergencyState = emergencyState;
	sessionStorage.volunteer = volunteer;
	location.href='viewEmergencySituation.html';
}