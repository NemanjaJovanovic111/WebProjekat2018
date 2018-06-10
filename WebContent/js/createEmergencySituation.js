$(document).ready(function(){
	
	loadTerritories();
	
	$("#registerSituationButton").click(function() {
		
		var formData = new FormData();
		formData.append('locationName', $("#locationName").val());
		formData.append('municipalitie', $("#municipalitie").val());
		formData.append('description', $("#description").val());
		formData.append('gMap', $("#gMap").val());
		formData.append('territory', $("#territories option:selected").val());
		formData.append('emergencyType', $("input[name=emergencyType]:checked").val());
		formData.append('image', document.querySelector('input[type=file]').files[0]);
		formData.append('emergencyState', $("input[name=emergencyState]:checked").val());
		
		$.ajax({
	      url: "../WebProjekat/rest/emergencyService/emergencyCreate",
	      type: 'POST',
	      xhr: function() {  // Custom XMLHttpRequest
	        var myXhr = $.ajaxSettings.xhr();
	        return myXhr;
	      },
	      success: function(data) {
	    	  window.history.back();
	      },
	      data: formData,
	      cache: false,
	      contentType: false,
	      processData: false
	    });
	});
	
});

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