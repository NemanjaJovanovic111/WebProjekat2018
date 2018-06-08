$(document).ready(function() {
	getMyEmergencies();
	
});

function getMyEmergencies() {
	
	$.ajax({
		url: "../WebProjekat/rest/emergencyService/getByVolUsername",
		type: "GET",
		data: {"volUsername" : sessionStorage.username},
		success : function (emergencies) {
			fillTable(emergencies);		
		}
	});
	
}

function fillTable(emergencies) {
	
	$.each(emergencies, function (index, emergency) {
		
	    $('#myEmergencySituationTable').append(
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
	    		$('<td id="terr' + index + '">'),
	    		$('<td>', {
	    			text: emergency.emergencyType
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