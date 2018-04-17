function changePageAndSize() {
	//$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/products?pageSize=" + $('#pageSizeSelect').val() + "&page=1&dispatch=changePageAndSize");
	//});
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