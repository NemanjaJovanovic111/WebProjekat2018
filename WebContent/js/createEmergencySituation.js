$(document).ready(function(){
	
	$.get("../WebProjekat/rest/territoryService/getAll", function(territories){
		$.each(territories, function (index, territory) {
		    $('#territories').append($('<option>', { 
		        value: territory.id,
		        text : territory.name
		    }));
		});
    });
	
	
	$("#registerSituationButton").click(function() {
		
		
		
		var formData = new FormData();
		formData.append('locatioName', $("#locationName").val());
		formData.append('municipalitie', $("#municipalitie").val());
		formData.append('description', $("#description").val());
		formData.append('dateTime', $("#dateTime").val());
		formData.append('gMap', $("#gMap").val());
		formData.append('emergencyType', $('input[name=name_of_your_radiobutton]:checked').val());
		formData.append('territory', "territory");
		formData.append('image', document.querySelector('input[type=file]').files[0]);
		$('input[name=name_of_your_radiobutton]:checked').val()
		
		$.ajax({
	      url: "../WebProjekat/rest/registerService/register",
	      type: 'POST',
	      xhr: function() {  // Custom XMLHttpRequest
	        var myXhr = $.ajaxSettings.xhr();
	        return myXhr;
	      },
	      success: function(data) {
	    	  console.log("SERVER RESPONSE ARRIVED!");
	      },
	      data: formData,
	      cache: false,
	      contentType: false,
	      processData: false
	    });
	});
	
});