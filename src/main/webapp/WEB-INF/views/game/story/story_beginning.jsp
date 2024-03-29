<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/css/story_main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/css/logo.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/css/btn.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

	function choice(){
			$.ajax({
				url: 'game/character/character_choice_form.do',
				success: function(res_data){
					$('#disp').html(res_data);
				}
			})
	}
	
	var i = 0;
	/* var txt = '아주 평화로운 마을에 살고 있는 당신은 여느 때와 다름없이 코딩을 하고 있었어요, 에러에 지친 당신을 위로해주는 것은 당신의 강아지 밖에 없습니다. 오늘도 당신은 어김없이 에러와 싸우고 집으로 돌아왔습니다. 어라? 당신을 반겨주는 강아지가 보이지 않습니다.';
	 */var speed = 50;
	
	function typeWriter() {
		  if (i < txt.length) {
		    document.getElementById("story_txt").innerHTML += txt.charAt(i);
		    i++;
		    setTimeout(typeWriter, speed );
		  }
		}
	
	
	$( document ).ready( function() {
		
		$( '#story_textbox' ).fadeToggle(0);
        $( '#story_textbox' ).fadeIn( 2000, function(){
        	typeWriter();
        });

    });
		
	

	
</script>
</head>
<body>
<div id="box">
<!------------------------------------------------------------------------>
   <div id="header">
      <a href="index.jsp">
         <img id="logo" src="${ pageContext.request.contextPath }/resources/img/logo.png">
      </a>               
   </div>
<!------------------------------------------------------------------------>
   <div id="content">
   	 	<div id="story_back" style="background-image: url('resources/img/back1_op.png');">
   	 		<div id="story_textbox" style="background-image: url('resources/img/story_textbox.png');">
   	 			<textarea id="story_txt" readonly="readonly"></textarea>
   	 			<div class="btn-container">
			  <a href="javascript:choice();" class="btn-3d green" style="margin-top: 30px;">캐릭터선택</a>
			</div>
   	 		</div>
	   		<!-- <br><button onclick="choice();">캐릭터 선택</button> -->
	   	</div>
   </div>
<!------------------------------------------------------------------------>
   <div id = "footer">
      <p id="copyright">Copyright 2022.Chasing Paws All rights reserved.</p>
   </div>
<!------------------------------------------------------------------------>   
</div>   

</body>
</html>