function loadContact() {
	var selectedMain = $("#navMainPageHome");
	selectedMain.addClass('class');
	selectedMain.attr('class', 'nav-item');
	
	var selectedAbout = $("#navMainPageAbout");
	selectedAbout.addClass('class');
	selectedAbout.attr('class', 'nav-item');
	
	var selectedServices = $("#navMainPageServices");
	selectedServices.addClass('class');
	selectedServices.attr('class', 'nav-item');
	
	var selectedContact = $("#navMainPageContact");
	selectedContact.addClass('class');
	selectedContact.attr('class', 'nav-item active');
}