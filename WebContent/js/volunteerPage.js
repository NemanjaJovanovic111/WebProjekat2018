$(document).ready(function() {
	getActiveEmergencies();
	loadTerritories();
	
	$("#filterButton").click(function() {
		filter();
	})
	
	$("#resetFilterButton").click(function() {
		filterReset();
	})
	
	$("#searchButton").click(function() {
		search();
	})
	
	$("#resetSearchButton").click(function() {
		searchReset();
	})
	
	$("#btnCombine").click(function() {
		combine();
	})
	
});

function getActiveEmergencies() {
	$.get("../WebProjekat/rest/emergencyService/getActive", function(emergencies) {
		$.each(emergencies, function (index, emergency) {
			setEmergencyTerretoryName(emergency);		
		});
		gAllEmergencies = gAllEmergencies.concat([], emergencies);
    });
}

var gAllEmergencies = [];
var gFilteredEmergencies = [];

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


function fillTable(emergencies) {
	
	$.each(emergencies, function (index, emergency) {
		
	    $('#emergencies').append(
	    	$('<tr>').append(
	    		$('<td>').append(
	    			$('<input type="button" value="View" onclick="viewEmergency(\'' + emergency.id + '\',\'' + 
	    					emergency.locationName + '\',\'' + emergency.municipalitie + '\',\'' + emergency.description + 
	    					'\',\'' + emergency.dateTime + '\',\'' + emergency.gMapLoc + '\',\'' + emergency.territory.name + 
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
	    		$('<td>', {
	    			text: emergency.territory.name
	    		}),
	    		$('<td>', {
	    			text: emergency.emergencyType
	    		}),
	    		$('<td>', {
	    			text: emergency.volunteer
	    		})
	    	)
	    );
	
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

function loadTerritories() {
	$.get("../WebProjekat/rest/territoryService/getAll", function(territories){
		$.each(territories, function (index, territory) {
			if(territory.id !== "null") {
			    $('#territories').append($('<option>', { 
			        value: territory.name,
			        text : territory.name
			    }));
			}
		});
    });
}

function filter() {
	var filteredEmergencies = performFilter(gAllEmergencies);
	clearTable();
	fillTable(filteredEmergencies);
}

function performFilter(emergencies) {
	var result = [];
	var type = $("#emergencyType").val();
	var territory = $("#territories").val();
	var filterDate = $('#dateFilter').is(':checked');
	
	for(var i = 0; i < emergencies.length; i++) {
		if (type !== "null" && emergencies[i].emergencyType !== type) {
			continue;
		}
	
		if (territory !== "null" && emergencies[i].territory.name !== territory) {
			continue;
		}

		result.push(emergencies[i]);
	}

	if(filterDate) {
		result = dateFilter(result);
	}
	return result;
}

function dateFilter(emergencies) {
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

	return emergencies;
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

function filterReset() {
	$("#emergencyType").val("null");
	$("#territories").val("null");
	$("#dateFilter").prop('checked', false);
}

function search() {
	var resultEmergencies = performSearch(gAllEmergencies);
	clearTable();
	fillTable(resultEmergencies);
}

function performSearch(emergencies) {
	var result = [];
	for (var i = 0; i < emergencies.length; i++) {

		if ($("#searchNazivOpstine").val() && $("#searchNazivOpstine").val() !== ""
			&& emergencies[i].municipalitie.indexOf($("#searchNazivOpstine").val()) === -1) {
			continue;
		}

		if ($("#searchOpis").val() && $("#searchOpis").val() !== ""
			&& emergencies[i].description.indexOf($("#searchOpis").val()) === -1) {
			continue;
		}

		if ($("#searchNazivTeritorije").val() && $("#searchNazivTeritorije").val() !== ""
			&& emergencies[i].territory.name.indexOf($("#searchNazivTeritorije").val()) === -1) {
			continue;
		}
		
		var nullVol = $('#nullVol').is(':checked');
		if(nullVol) {
			if(emergencies[i].volunteer !== "") {
				continue;
			}
		}
		else {
			if($("#searchVolonter").val() && $("#searchVolonter").val() !== ""
				&& emergencies[i].volunteer.indexOf($("#searchVolonter").val()) === -1) {
				continue;
			}
		}

		result.push(emergencies[i]);
	}
	return result;

}

function searchReset() {
	$("#searchNazivOpstine").val("");
	$("#searchOpis").val("");
	$("#searchNazivTeritorije").val("");
	$("#searchVolonter").val("");
	$("#nullVol").prop('checked', false);
}

function combine() {
	var resultEmergencies = [];
	resultEmergencies = performSearch(gAllEmergencies);
	resultEmergencies = performFilter(resultEmergencies);
	clearTable();
	fillTable(resultEmergencies);
}