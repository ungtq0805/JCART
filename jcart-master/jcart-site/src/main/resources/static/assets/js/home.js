function onLoadHome() {
	var ids = [];
	$("#listGroupOfCat").find("a").each(function(){ 
		ids.push($(this).attr("id")); 
	});
	
	var firstGroupCat = $("#" + ids[0]);
	firstGroupCat.addClass('class');
	firstGroupCat.attr('class', 'list-group-item active');
}

