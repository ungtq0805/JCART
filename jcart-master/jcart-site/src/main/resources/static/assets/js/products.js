function selectedGroupCat() {
	var catSelectedIdVal = $("#catSelectedId").val();
	var selectedGroupCat = $("#cat" + catSelectedIdVal);
	
	selectedGroupCat.addClass('class');
	selectedGroupCat.attr('class', 'list-group-item active');
}

function onLoadProducts() {
	loadNavMenu();
	selectedGroupCat();
}

function onLoadProductDetail() {
	selectedGroupCat();
}

function loadNavMenu() {
	var selectedMain = $("#navMainPageHome");
	selectedMain.addClass('class');
	selectedMain.attr('class', 'nav-item');
	
	var selectedProducts = $("#navMainPageProducts");
	selectedProducts.addClass('class');
	selectedProducts.attr('class', 'nav-item active');
	
	var selectedAbout = $("#navMainPageAbout");
	selectedAbout.addClass('class');
	selectedAbout.attr('class', 'nav-item');
	
	var selectedServices = $("#navMainPageServices");
	selectedServices.addClass('class');
	selectedServices.attr('class', 'nav-item');
	
	var selectedContact = $("#navMainPageContact");
	selectedContact.addClass('class');
	selectedContact.attr('class', 'nav-item');
}