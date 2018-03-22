"use strict";

(function() {

$(document).ready(function() {

	$(".rentingButton").click(function() {

		$.ajax({
			
			url : '/lockersAjax1/' + this.id,
			method : 'POST',
			dataType : 'json',
			async : true,
			cache : false,
			timeout : 2000,
			data : {},
			
			success : function(response) {
				console.log(response);
				
				var lockerContainer = $('#lockerContainer');
				lockerContainer.empty();
				
				response.forEach(function(item) {					
					var lockerItemClass = $('<div class="lockerItemClass" />');
					
					var lockerItemLeftSideDiv = $('<div class="lockerItemLeftSide textCenterCenter" />');
					var lockerItemLeftSideDivParagraph = $('<p />').text("Number: " + item.id);
					lockerItemLeftSideDiv.append(lockerItemLeftSideDivParagraph);
					
					var lockerItemRightSideDiv = $('<div class="lockerItemRightSide" />');
					var buttonEnumValue = item.rentedByEnum.toString();
					var buttonText;
					switch(buttonEnumValue) {
						case "NOT_RENTED":
							buttonText = "Not rented!";
							break;
						case "RENTED_BY_YOU":
							buttonText = "Rented by you!";
							break;
						case "RENTED_BY_OTHER":
							buttonText = "Rented by other!";
							break;
						default:
							buttonText = "ERROR";
					}
					var lockerItemRightSideDivButton = $('<button class="rentingButton" />').text(buttonText);
					lockerItemRightSideDivButton.attr("id", item.id);

					lockerItemRightSideDiv.append(lockerItemRightSideDivButton);
					
					lockerItemClass.append(lockerItemLeftSideDiv);
					lockerItemClass.append(lockerItemRightSideDiv);
					
					lockerContainer.append(lockerItemClass);
				});

				$.getScript('js/set-locker-status.js');
				
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