function changePageAndSize() {
	window.location.replace("/chat/users?pageSize=" + $('#pageSizeSelect').val() + "&page=1&dispatch=changePageAndSize");
}