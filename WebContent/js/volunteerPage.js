$(document).ready(function() {
	getActiveEmergencies();
	loadTerritories();

});

function getActiveEmergencies() {
	$.get("../WebProjekat/rest/emergencyService/getActive", function(emergencies) {
		fillTable(emergencies);
    });
	
	$.get("../WebProjekat/rest/emergencyService/withoutVolunteer", function(emergencies) {
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

function typeFilter() {
	var type = $("#emergencyType").val();
	$.ajax({
		url: "rest/emergencyService/getByType/",
		type: "GET",
		data: {"type" : type},
		success : function (emergencies) {
			fillTable(emergencies);		
		}
	});
}

function loadTerritories() {
	$.get("../WebProjekat/rest/territoryService/getAll", function(territories){
		$.each(territories, function (index, territory) {
			if(territory.id !== "null") {
			    $('#territories').append($('<option>', { 
			        value: territory.id,
			        text : territory.name
			    }));
			}
		});
    });
}

function territoryFilter() {
	var territory = $("#territories").val();
	$.ajax({
		url: "rest/emergencyService/getByTerritory/",
		type: "GET",
		data: {"territory" : territory},
		success : function (emergencies) {
			fillTable(emergencies);		
		}
	});
}

function dateFilter() {
	$.get("../WebProjekat/rest/emergencyService/getActive", function(emergencies) {
		if(emergencies.length > 1) {
			for(var i = 0; i < emergencies.length; i++) {
				
				var emergencyWithMinDate = emergencies[i];
				var indexOfEmergencyWithMinDate = i;
				
				for(var j = i + 1; j < emergencies.length; j++) {
					var currDateTime = parseDateTime(emergencies[j]);
					var currDate = currDateTime.date;
					var currTime = currDateTime.time;
					
					var minDateTime = parseDateTime(emergencyWithMinDate);
					var minDate = minDateTime.date;
					var minTime = minDateTime.time;
					if(compare(minDate, minTime, currDate, currTime)) {
						emergencyWithMinDate = emergencies[j];
						indexOfEmergencyWithMinDate = j;
					}
				}
				var temp = emergencies[i];
				emergencies[i] = emergencyWithMinDate;
				emergencies[indexOfEmergencyWithMinDate] = temp;
			}
		}
		
		fillTable(emergencies);
    });
}

function parseDateTime(emergency) {
	var split = emergency.dateTime.split(" ");
	
	var date = split[0];
	var dateSplit = date.split(".");
	
	var day = dateSplit[0];
	if(day.length == 1)
		day = "0" + day;
	
	var month = dateSplit[1];
	if(month.length== 1)
		month = "0" + month;
	
	var year = dateSplit[2];
	
	var time = split[1];
	var timeSplit = time.split(":");
	var hour = timeSplit[0];
	var minute = timeSplit[1];
	
	return {date : year + "." + month + "." + day, time : hour + ":" + minute}
}

function compare(date1, time1, date2, time2) {
	if(date1 > date2)
		return true;
	else if(date1 === date2) {
		if(time1 > time2)
			return true;
		else
			return false;
	}
	else
		return false;
}