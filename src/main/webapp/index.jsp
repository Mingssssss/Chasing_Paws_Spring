<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	var stage_val = 0;
	$(function() {
		$.ajax({
			url : 'user/main.do',
			success : function(res_data) {
				$('#disp').html(res_data);
			}
		})
	});
</script>
</head>
<body>

	<div id="disp"></div>

</body>
</html>