$(document).ready(function() {
	//$('#rdCurrentDay').attr('checked', 'checked');
	
	$("#rdCurrentMonth").click(function(){ 
	    $('#rdCurrentMonth').prop("checked", true);// .attr("checked", "checked");
	    
	    var url = '/home/by_month';
		window.location = url;
	});
	
	$("#rdCurrentDay").click(function(){ 
	    $('#rdCurrentDay').attr("checked", "checked");
	    
	    var url = '/home/by_day';
		window.location = url;
	});
});

