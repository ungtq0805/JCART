function loadProduct() {
	$('#selectedCat').val($('#catId').val());
}

function changePageAndSize() {
	window.location.replace("/products?pageSize=" + 
			$('#pageSizeSelect').val() + 
			"&page=1&dispatch=changePageAndSize" + 
			"&selectedCat=" + $('#selectedCat').val());
}

function showDeleteModal(catId) {
   	$('#productId').val(catId);
}

function deleteProduct() {
   	var productId = $('#productId').val();
   	var url = '/products/remove/' + productId;
	window.location = url;
}

function backToList() {
	$('#frmProduct').attr('action', "/products/back").submit();
}

function changeCateOnProducts() {
	changePageAndSize();
}