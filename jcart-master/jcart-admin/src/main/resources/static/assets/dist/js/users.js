function changePageAndSize() {
	window.location.replace("/users?pageSize=" + $('#pageSizeSelect').val() + "&page=1&dispatch=changePageAndSize");
}

function showDeleteModal(userId) {
	$('#userId').val(userId);
}

function deleteUser() {
	 var userId = $('#userId').val();
	 var url = '/users/remove/' + userId;
	 window.location = url;
}

function backToList() {
	$('#frmUser').attr('action', "/users/back").submit();
}