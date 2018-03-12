$(document).ready(function() {
	var localesId = "#locales"
	
    $(localesId).change(function () {
        var selectedOption = $(localesId).val();
        if (selectedOption != '') {
        	window.location.replace('?lang=' + selectedOption);
        }
    });
});