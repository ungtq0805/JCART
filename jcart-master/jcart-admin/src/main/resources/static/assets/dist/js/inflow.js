$(document).ready(function() {
	changePageAndSize();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/wh/inflows?pageSize=" + this.value + "&page=1&dispatch=changePageAndSize");
	});
}

function showDeleteModal(whId) {
	$('#whInflowId').val(whId);
}

function deleteWh() {
	var whInflowId = $('#whInflowId').val();
	var url = '/wh/inflow/delete/' + whInflowId;
	window.location = url;
}

function backToList() {
	$('#frmInflow').attr('action', "/inflows/back").submit();
}