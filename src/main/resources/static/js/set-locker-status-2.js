"use strict";

(function() {

$(document).ready(function() {

	var reservationListTemplateSource = $("#locker-container-template").html();       // get the template's html source
    var reservationListTemplate = Handlebars.compile(reservationListTemplateSource); // initialize Handlebars template
	
	$(".rentingButton").click(function() {

		$.ajax({
			
			url : '/lockersAjax/' + this.id,
			method : 'POST',
			dataType : 'json',
			async : true,
			cache : false,
			timeout : 2000,
			data : {},
			
			success : function(response) {
				console.log(response);
				
				var h = reservationListTemplate(response);     // generate HTML from the object using the template	        
				$("#reservation-list").empty();
		        $("#reservation-list").append(h);              // insert the generated HTML into the document
				
		        
		        
//				var lockerContainer = $('#lockerContainer');
//				lockerContainer.empty();
//				
//				response.forEach(function(item) {					
//					var lockerItemClass = $('<div class="lockerItemClass" />');
//					
//					var lockerItemLeftSideDiv = $('<div class="lockerItemLeftSide textCenterCenter" />');
//					var lockerItemLeftSideDivParagraph = $('<p />').text("Number: " + item.id);
//					lockerItemLeftSideDiv.append(lockerItemLeftSideDivParagraph);
//					
//					var lockerItemRightSideDiv = $('<div class="lockerItemRightSide" />');
//					var buttonEnumValue = item.rentedByEnum.toString();
//					var buttonText;
//					switch(buttonEnumValue) {
//						case "NOT_RENTED":
//							buttonText = "Not rented!";
//							break;
//						case "RENTED_BY_YOU":
//							buttonText = "Rented by you!";
//							break;
//						case "RENTED_BY_OTHER":
//							buttonText = "Rented by other!";
//							break;
//						default:
//							buttonText = "ERROR";
//					}
//					var lockerItemRightSideDivButton = $('<button class="rentingButton" />').text(buttonText);
//					lockerItemRightSideDivButton.attr("id", item.id);
//
//					lockerItemRightSideDiv.append(lockerItemRightSideDivButton);
//					
//					lockerItemClass.append(lockerItemLeftSideDiv);
//					lockerItemClass.append(lockerItemRightSideDiv);
//					
//					lockerContainer.append(lockerItemClass);
//				});
//
//				$.getScript('js/set-locker-status.js');
//				
				console.log("DONE");
			},
			error : function(response, textStatus, errorThrown) {
				console.log("error");
				console.log(response);
			}
			
		});
	});
});

})();