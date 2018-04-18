function loadBody() {
	var inventoryParentLi = $('#inventoryParentLi');
	inventoryParentLi.addClass('class');
	inventoryParentLi.attr('class', 'active');
	
	var navInventory = $('#navInventory');
	navInventory.addClass('aria-expanded');
	navInventory.attr('aria-expanded', 'true'); 
}

function backToList() {
	$('#whWareHouseForm').attr('action', "/warehouse/back").submit();
}

function backToListInflow() {
	$('#frmUpdateInflow').attr('action', "/inflows/back").submit();
}

function backToListInStock() {
	$('#frmCreateOutflow').attr('action', "/wh/stocks/back").submit();
}