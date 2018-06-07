$(document).ready(function(){
	
	getAll();
	
	$("#saveEditTerritory").click (function(){
		$.ajax({
			url: "../WebProjekat/rest/territoryService/updateTerritory",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(controller.territories),
			success: function() {
				window.location.reload();
			}
		});
	});
	
});

var controller = {
	territories: []
};

function getAll() {
	$.get("../WebProjekat/rest/territoryService/getAll", function(territories){
		controller.territories = territories;
		$.each(territories, function (index, territory) {
			if (territory.id !== "null")
				addTerritoryToTable(index, territory);
		});
    });
}


function addTerritoryToTable(index, territory) {
	$('#editTerritoryTable').append(
		$('<tr>').append(
			$('<td>').append(
				$('<input type="text" value="' + territory.name + '" onchange="setTerritoryName(\'' + territory.id + '\', this.value)">')
			),
			$('<td>').append(
				$('<input type="number" value="' + territory.area + '" onchange="setTerritoryArea(\'' + territory.id + '\', this.value)">')
			),
			$('<td>').append(
					$('<input type="number" value="' + territory.population + '" onchange="setTerritoryPopulation(\'' + territory.id + '\', this.value)">')
			)
		)
	)
}

function setTerritoryName(territoryId, name) {
	for(var i = 0; i < controller.territories.length; i++) {
		if(controller.territories[i].id === territoryId) {
			controller.territories[i].name = name;
		}
	}
}

function setTerritoryArea(territoryId, area) {
	for(var i = 0; i < controller.territories.length; i++) {
		if(controller.territories[i].id === territoryId) {
			controller.territories[i].area = area;
		}
	}
}

function setTerritoryPopulation(territoryId, population) {
	for(var i = 0; i < controller.territories.length; i++) {
		if(controller.territories[i].id === territoryId) {
			controller.territories[i].population = population;
		}
	}
}

