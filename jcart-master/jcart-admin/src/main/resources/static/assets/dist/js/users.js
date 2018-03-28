$(document).ready(function() {
	changePageAndSize();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/users?pageSize=" + this.value + "&page=1");
	});
}

function showDeleteModal(userId) {
	$('#userId').val(userId);
}

function deleteUser() {
	 var userId = $('#userId').val();
	 var url = '/users/remove/' + userId;
	 window.location = url;
}