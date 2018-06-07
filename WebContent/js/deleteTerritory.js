$(document).ready(function() {
	
	loadTerritoriesInTable();
	
	$("#deleteButton").click(function(){
		$.ajax({
			url: "../WebProjekat/rest/territoryService/deleteTerritories",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(controller.territoriesForDeletion),
			success: function() {
				window.location.reload();
			}
		});
	});
	
});

var controller = {
    territoriesForDeletion: []
};

function loadTerritoriesInTable() {
	$.get("../WebProjekat/rest/territoryService/getAll", function(territories) {
		$.each(territories, function (index, territory) {
			if(territory.id !== "null"){
			    $('#deleteTerritoryTable').append(
			    	
			    	$('<tr>').append(
			    		$('<td>', {text: territory.name}),
			    		$('<td>').append(
			    			$('<input onchange="setForDeletion(this);" type="checkbox", value="' + territory.id +'">')
			    		)
			    	)    	
			    )
			}
		});
    });
}

function setForDeletion(checkbox) {

	var territoryForDeletionId = checkbox.value;
	
	if(checkbox.checked == true)
		// add territoryForDeletionId to controller.territoriesForDeletion array
		controller.territoriesForDeletion.push(territoryForDeletionId);
	else {
		// remove territoryForDeletionId from controller.territoriesForDeletion array
		var index = controller.territoriesForDeletion.indexOf(territoryForDeletionId);
		if (index > -1)
			controller.territoriesForDeletion.splice(index, 1);
	}
}
