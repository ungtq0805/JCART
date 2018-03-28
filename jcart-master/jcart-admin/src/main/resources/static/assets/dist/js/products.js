$(document).ready(function() {
	changePageAndSize();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/products?pageSize=" + this.value + "&page=1");
	});
}

function showDeleteModal(catId) {
   	$('#productId').val(catId);
}
   
function deleteProduct() {
   	var productId = $('#productId').val();
   	var url = '/products/remove/' + productId;
	window.location = url;
}