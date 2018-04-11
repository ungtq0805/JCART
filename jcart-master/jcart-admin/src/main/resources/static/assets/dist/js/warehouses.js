function loadBody() {
	var inventoryParentLi = $('#inventoryParentLi');
	inventoryParentLi.addClass('class');
	inventoryParentLi.attr('class', 'active');
	
	var navInventory = $('#navInventory');
	navInventory.addClass('aria-expanded');
	navInventory.attr('aria-expanded', 'true'); 
}