function onLoadProducts() {
	var catSelectedIdVal = $("#catSelectedId").val();
	var selectedGroupCat = $("#cat" + catSelectedIdVal);
	
	selectedGroupCat.addClass('class');
	selectedGroupCat.attr('class', 'list-group-item active');
}

