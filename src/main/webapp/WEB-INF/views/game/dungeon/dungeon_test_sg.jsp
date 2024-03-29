<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/resources/css/game_main.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/resources/css/logo.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap')
	;
</style>
<script type="text/javascript">
   $(function(){
      alert(stage_val + " 스테이지");
   })
   
   function choice(){
      $.ajax({
         url: '${ pageContext.request.contextPath }/game/shop/shop.do',
         success: function(res_data){
            $('#disp').html(res_data);
         }
      })
   }
   
   function aa(){
	      var auto_attack = 0;
	      $.ajax({
	         url: 'game/dungeon/battle/attack.do',
	         data: {'s_idx': auto_attack},
	         dataType: 'json',
	         success: function(res_data){
	            cool_time(res_data);
	            $('#main_ch_hp').html('hp: '+ res_data.main_ch.c_hp);
	             $('#hp_bar_main_ch > span').html(res_data.main_ch.c_hp+"/"+${ main_ch.c_hp });
	             $('#hp_bar_main_ch').width(100 * res_data.main_ch.c_hp / ${ main_ch.c_hp } +"%");
	             $('#hp_bar_mop > span').html(res_data.mop.m_hp+"/"+${ mop.m_hp });
	             $('#hp_bar_mop').width(100 * res_data.mop.m_hp / ${ mop.m_hp } +"%");
	            
	            //스탯
	             $('#main_ch_ad').html('ad: '+ res_data.main_ch.c_ad);
	             $('#main_ch_ap').html('ap: '+ res_data.main_ch.c_ap);
	             $('#main_ch_armor').html('armor: '+ res_data.main_ch.c_armor+"(피해감소 "+ Math.round(100-5000/(res_data.main_ch.c_armor+50)) +"%)");
	             $('#main_ch_critical').html('critical: '+ res_data.main_ch.c_critical);
	             $('#main_ch_avd').html('avd: '+ res_data.main_ch.c_avd);
	             
	             $('#mop_ad').html('ad: '+ res_data.mop.m_ad);
	             $('#mop_armor').html('armor: '+ res_data.mop.m_armor);
	             
	            var content = $("#txt").val();
	            content = content  + res_data.attack_mop_vo.battle_info + "\n"
	                           +   res_data.attack_main_ch_vo.battle_info + "\n"
	                           + "-------------------------------------------------------\n";
	            $("#txt").html(content); 
	            document.getElementById("txt").scrollTop = document.getElementById("txt").scrollHeight;
	            
	         }
	      })
	      
	   }
   
   function cool_time(res_data){
	      console.log("쿨타임함수로 들어옴")
	      console.log(res_data.main_ch.active_skill_remaining_turn);
	      if(res_data.main_ch.active_skill_remaining_turn[${ s_num[0]-1 }]!=0)
	         $($("[id='btn_skill_a']").get(0)).css('pointer-events', 'none');
	      else
	         $($("[id='btn_skill_a']").get(0)).css('pointer-events', 'auto');
	         
	      if(res_data.main_ch.active_skill_remaining_turn[${ s_num[1]-1 }]!=0)
	         $($("[id='btn_skill_a']").get(1)).css('pointer-events', 'none');
	      else
	         $($("[id='btn_skill_a']").get(1)).css('pointer-events', 'auto');
	      
	      if(res_data.main_ch.active_skill_remaining_turn[${ s_num[2]-1 }]!=0)
	         $($("[id='btn_skill_a']").get(2)).css('pointer-events', 'none');
	      else
	         $($("[id='btn_skill_a']").get(2)).css('pointer-events', 'auto');
	      
	      if(res_data.main_ch.active_skill_remaining_turn[${ s_num[3]-1 }]!=0){
	    	  
	      		console.log("???");
	         $($("[id='btn_skill_a']").get(3)).css('pointer-events', 'none');
	      }
	      else
	         $($("[id='btn_skill_a']").get(3)).css('pointer-events', 'auto');
	   }
   
   function skill(s_idx){
	      $.ajax({
	         url: 'game/dungeon/battle/attack.do',
	         data: {'s_idx': s_idx},
	         dataType: 'json',
	         success: function(res_data){
	            
	            cool_time(res_data);
	            
	            $('#main_ch_hp').html('hp: '+ res_data.main_ch.c_hp);
	            $('#hp_bar_main_ch > span').html(res_data.main_ch.c_hp+"/"+${ main_ch.c_hp });
	            $('#hp_bar_main_ch').width(100 * res_data.main_ch.c_hp / ${ main_ch.c_hp } +"%");
	            $('#hp_bar_mop > span').html(res_data.mop.m_hp+"/"+${ mop.m_hp });
	            $('#hp_bar_mop').width(100 * res_data.mop.m_hp / ${ mop.m_hp } +"%");
	            
	            //스탯
	            $('#main_ch_ad').html('ad: '+ res_data.main_ch.c_ad);
	            $('#main_ch_ap').html('ap: '+ res_data.main_ch.c_ap);
	            $('#main_ch_armor').html('armor: '+ res_data.main_ch.c_armor+"(피해감소 "+ Math.round(100-5000/(res_data.main_ch.c_armor+50)) +"%)");
	            $('#main_ch_critical').html('critical: '+ res_data.main_ch.c_critical);
	            $('#main_ch_avd').html('avd: '+ res_data.main_ch.c_avd);
	            
	            $('#mop_ad').html('ad: '+ res_data.mop.m_ad);
	            $('#mop_armor').html('armor: '+ res_data.mop.m_armor);
	            
	            var content = $("#txt").val();
	            
	            content = content  + res_data.attack_mop_vo.battle_info + "\n"
                +   res_data.attack_main_ch_vo.battle_info + "\n";
				if (res_data.attack_mop_vo.battle_info_p != null) {
				content += res_data.attack_mop_vo.battle_info_p;
				}
					            
	            $("#txt").html(content); 
	            
	            if(res_data.extra_skill_main_ch != ""){
	               $("#txt").css("color", "red");
	               var content2 = $("#txt").val();
	               content2 = content2  + res_data.extra_skill_main_ch + "\n"
	               $("#txt").html(content2); 
	               
	               $("#txt").css("color", "black");
	            }
	            
	            if(res_data.extra_skill_mop != ""){
	               $("#txt").css("color", "red");
	               var content3 = $("#txt").val();
	               content3 = content3  + res_data.extra_skill_mop + "\n"
	               $("#txt").html(content3); 
	               
	               $("#txt").css("color", "black");
	            }
	            var content0 = $("#txt").val();
	            content0 = content0  + "-------------------------------------------------------\n";
	            $("#txt").html(content0); 
	            
	            if(res_data.mop.m_hp<=0)
	               next_stage();
	            else if(res_data.main_ch.c_hp<=0)
	               previous_shop();
	         }
	      });
	   }
   
   function next_stage(){
      //스테이지 정보 ++
      alert("적을 무찔렀습니다");
      alert("다음 스테이지로 넘어갑니다");
      stage_val++;
      $.ajax({
         url: 'game/shop/shop.do',
         data: {"stage_val": stage_val},
         success: function(res_data){
            $('#disp').html(res_data);
         }
      }) 
   }
   
   function previous_shop(){
      //스테이지 정보--
      alert("사망하였습니다");
      alert("상점에서 부활합니다");
      $.ajax({
         url: 'game/shop/shop.do',
         data: {"stage_val": stage_val},
         success: function(res_data){
            $('#disp').html(res_data);
         }
      }) 
   }
   
   
   
</script>
</head>
<body>
	<div id="box">
		<!------------------------------------------------------------------------>
		<div id="header">
			<a href="index.jsp"> <img id="logo"
				src="${ pageContext.request.contextPath }/resources/img/logo.png">
			</a>
		</div>
		<!------------------------------------------------------------------------>
		<div id="content">
			<div id="right">
				<div id="game_page"
					style="background-image: url('resources/img/game_bg_1.png');">
					<div>
						<div id="guard_info" style="margin-top: 50px; margin-left: 10px;">
							<table border="1" style="margin: 5px;">
								<tr>
									<td id="items"><img id="items" src="${ pageContext.request.contextPath }/resources/img/item/${ main_ch.item_list[0].i_img }"  onError="this.style.visibility='hidden'" ></td>
								</tr>
								<tr>
									<td id="items"><img id="items" src="${ pageContext.request.contextPath }/resources/img/item/${ main_ch.item_list[1].i_img }" onError="this.style.visibility='hidden'" ></td>
								</tr>
								<tr>
									<td id="items"><img id="items" src="${ pageContext.request.contextPath }/resources/img/item/${ main_ch.item_list[2].i_img }" onError="this.style.visibility='hidden'" ></td>
								</tr>
								<tr>
									<td id="items"><img id="items" src="${ pageContext.request.contextPath }/resources/img/item/${ main_ch.item_list[3].i_img }" onError="this.style.visibility='hidden'" ></td>
								</tr>
								<tr>
									<td id="items"></td>
								</tr>
							</table>
						</div>
					</div>

					<div id="user_hp_bar">
						<div>
							<div class="progress" style="width: 100%; margin-top: 60px;">
								<div id="hp_bar_main_ch"
									class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="100" aria-valuemin="0"
									aria-valuemax="${ main_ch.c_hp }" style="width: 100%;">
									<span>${ main_ch.c_hp }/${ main_ch.c_hp }</span>
								</div>
							</div>
							<img id="warrior"
								src="${ pageContext.request.contextPath }/resources/img/${ main_ch.c_img }">
						</div>
					</div>

					<div id="mop_hp_bar">
						<div>
							<div class="progress" style="width: 100%; margin-top: 60px;">
								<div id="hp_bar_mop" class="progress-bar progress-bar-danger"
									role="progressbar" aria-valuenow="200" aria-valuemin="0"
									aria-valuemax="${ mop.m_hp }" style="width: 100%;">
									<span>${ mop.m_hp }/${ mop.m_hp }</span>
								</div>
							</div>
							<img id="warrior1"
								src="${ pageContext.request.contextPath }/resources/img/mop/${ mop.m_img }">
						</div>
					</div>
				</div>


				<div id="state_story">
					<div id="story_page"
						style="background-image: url('resources/img/textarea.png');">
						<textarea id="txt" readonly="readonly"></textarea>
					</div>

					<div id="state_page">
						<div id="user_state">
							<table border="1" style="font-size: small;">
								<tr>
									<td>&nbsp;&nbsp;name: ${ main_ch.c_name }</td>
									<td id="main_ch_hp">&nbsp;&nbsp;hp: ${ main_ch.c_hp }</td>
									<td id="main_ch_ad">&nbsp;&nbsp;ad: ${ main_ch.c_ad }</td>
								</tr>
								<tr>
									<td id="main_ch_ap">&nbsp;&nbsp;ap: ${ main_ch.c_ap }</td>
									<td id="main_ch_avd">&nbsp;&nbsp;avd: ${ main_ch.c_avd }</td>
									<td id="main_ch_critical">&nbsp;&nbsp;critical: ${ main_ch.c_critical }</td>
								</tr>
								<tr>
									<td colspan="3" id="main_ch_armor">&nbsp;&nbsp; 
									armor: ${ main_ch.c_armor }(피해감소 <fmt:formatNumber value="${ 100-5000/(main_ch.c_armor+50)}" pattern="0"/> %)
									</td>
								</tr>
							</table>
						</div>

						<div id="btn_state">
							<a onclick="aa();"><img id="btn_skill1"
								src="${ pageContext.request.contextPath }/resources/img/skill/${ main_ch.c_auto_attack_img }"></a>
							<a onclick="shop();"><img id="btn_skill2"
								src="${ pageContext.request.contextPath }/resources/img/skill/${ main_ch.c_p_skill_img }"></a>
							<a id="btn_skill_a"  onclick="skill(${ main_ch.skill_vo[s_num[0] - 1].s_idx });"><img id="btn_skill"  src="${ pageContext.request.contextPath }/resources/img/skill/${ main_ch.skill_vo[s_num[0] - 1].s_img }"></a>
			               <a id="btn_skill_a"  onclick="skill(${ main_ch.skill_vo[s_num[1] - 1].s_idx });"><img id="btn_skill"  src="${ pageContext.request.contextPath }/resources/img/skill/${ main_ch.skill_vo[s_num[1] - 1].s_img }"></a>
			               <a id="btn_skill_a"  onclick="skill(${ main_ch.skill_vo[s_num[2] - 1].s_idx });"><img id="btn_skill"  src="${ pageContext.request.contextPath }/resources/img/skill/${ main_ch.skill_vo[s_num[2] - 1].s_img }"></a>
			               <a id="btn_skill_a"  onclick="skill(${ main_ch.skill_vo[s_num[3] - 1].s_idx });"><img id="btn_skill"  src="${ pageContext.request.contextPath }/resources/img/skill/${ main_ch.skill_vo[s_num[3] - 1].s_img }"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!------------------------------------------------------------------------>
		<div id="footer">
			<p id="copyright">Copyright 2022.Chasing Paws All rights
				reserved.</p>
		</div>
		<!------------------------------------------------------------------------>
	</div>

</body>
</html>