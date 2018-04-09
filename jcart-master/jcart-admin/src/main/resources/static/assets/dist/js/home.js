function onClickRdCurrentMonth() {
	$('#rdCurrentMonth').prop("checked", true);
    
    var url = '/home/by_month';
	window.location = url;
}

function onClickRdCurrentDay() {
	$('#rdCurrentDay').attr("checked", "checked");
    
    var url = '/home/by_day';
	window.location = url;
}
