
function checkpwd(box){
	if(box.value == null || box.value.length == 0)
		document.getElementsByName("subbutton")[0].disabled = true;
	else
		document.getElementsByName("subbutton")[0].disabled = false;
}