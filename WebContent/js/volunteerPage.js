$(document).ready(function() {
	getActiveEmergencies();

});

var controller = {
	activeEmergencies: []
} 

function getActiveEmergencies() {
	$.get("../WebProjekat/rest/emergencyService/getActive", function(emergencies) {
		fillTable(emergencies);
    });
}


function fillTable(emergencies) {
	$.each(emergencies, function (index, emergency) {
	    $('#emergencies').append(
	    	$('<tr>').append(
	    		$('<td>').append(
	    			$('<input type="button" value="View" onclick="viewEmergency(\'' + emergency + '\')"/>')	
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
	    			text: getDateTimeString(emergency.dateTime)
	    		}),
	    		$('<td>', {
	    			text: emergency.gMapLoc
	    		}),
	    		$('<td>', {
	    			text: emergency.territory
	    		}),
	    		$('<td>', {
	    			text: emergency.emergencyType
	    		}),
	    		$('<td>', {
	    			text: emergency.volunteer
	    		})
	    	));
	});
}

function getDateTimeString(dateTime) {
	var string = "";
	string += dateTime.dayOfMonth + ".";
	string += dateTime.monthOfYear + ".";
	string += dateTime.year + ". ";
	string += dateTime.hourOfDay + ":";
	string += dateTime.minuteOfHour;
	return string;
}

function viewEmergency(emergency) {
	//location.href='myEmergencySituation.html';
	
	console.log("show me: " + emergency);
}