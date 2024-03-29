package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CharacterDao;
import dao.DungeonDao;
import dao.MopDao;
import dao.SkillDao;
import info.character.Archer;
import info.character.Warrior;
import info.character.Wizard;
import info.mop.Mop1_1;
import info.mop.Mop1_2;
import info.mop.Mop1_3;
import info.mop.Mop1_4;
import info.mop.Mop2_1;
import info.mop.Mop2_2;
import info.mop.Mop2_3;
import info.mop.Mop2_4;
import info.mop.Mop3_1;
import info.mop.Mop3_2;
import info.mop.Mop3_3;
import info.mop.Mop3_4;
import vo.AttackVo;
import vo.CharacterVo;
import vo.MopVo;
import vo.SkillVo;

@Controller
@RequestMapping("/game/dungeon/")
public class DungeonController {

   @Autowired
   ServletContext application;

   @Autowired
   HttpServletRequest request;

   // 로그인 정보
   @Autowired
   HttpSession session;

   @Autowired
   DungeonDao dungeon_dao;

   @Autowired
   CharacterDao character_dao;

   @Autowired
   SkillDao skill_dao;

   MopDao mop_dao;

   public void setDungeon_dao(DungeonDao dungeon_dao) {
      this.dungeon_dao = dungeon_dao;
   }

   public void setSkill_dao(SkillDao skill_dao) {
      this.skill_dao = skill_dao;
   }

   public void setMop_dao(MopDao mop_dao) {
      this.mop_dao = mop_dao;
   }

   @RequestMapping("dungeon.do")
   public String dungeon(Model model, int stage_val, int[] s_idx, int[] s_val, int[] s_num) {

      // 캐릭터 정보랑 스킬 둘 다 넘겨야 됨
      // 스킬 mapper 만들고 s_idx 이용해서 캐릭터 정보 1개(application에 저장되어서 걍 넘기면 딤)
      // 넘어온 s_idx 4개로 선택된 스킬 이름만 main_ch에 넘겨서 jsp에서 출력
      // 몹 관한 정보는 db 구현 후 작성

      List<Integer> list = new ArrayList<>();

      // shop.jsp에서 조잡하게 가져와서 맨 뒤에서부터 8개가 전체 찍은 스킬에 대한 값
//      for (int i = s_val.length - 8; i < s_val.length; i++) {
//         list.add(s_val[i]);
//      }
//
//      int[] arr = new int[8];
//      int size = 0;
//
//      for (Integer temp : list) {
//         arr[size++] = temp;
//      }

      CharacterVo main_ch = (CharacterVo) application.getAttribute("main_ch");

//      for (int i = 0; i < 8; i++) {
//         main_ch.setActive_skill_level(arr);
//      }
      
      main_ch.setActive_skill_level(s_val);
      List<SkillVo> skill_list = new ArrayList<>();
      
      //stage_val을 4로 나눠서 몫 0이면 1스테이지
      /*
          1->1-1, 2-> 1-2, 3->1-3, 4->1-4
          5->2-1, 6-> 2-2, 7->2-3,8->2-4
          9->3-1, 10->3-2, 11->3-3,12->3-4
          (MopVo) mop_dao.selectOne(stage_val);
          하면 해당하는 MopVo가 나오고
          그걸 Mop?_?에 넣어야하는데
       */
      MopVo mop = null;
      switch(stage_val) {
      case 1 : mop = new Mop1_1();
      case 2 : mop = new Mop1_2();
      case 3 : mop = new Mop1_3();
      case 4 : mop = new Mop1_4();
      case 5 : mop = new Mop2_1();
      case 6 : mop = new Mop2_2();
      case 7 : mop = new Mop2_3();
      case 8 : mop = new Mop2_4();
      case 9 : mop = new Mop3_1();
      case 10 : mop = new Mop3_2();
      case 11 : mop = new Mop3_3();
      case 12 : mop = new Mop3_4();
      default: mop = new Mop1_1();
      }
      
      mop = mop_dao.selectOne(stage_val);
      mop.setM_original_hp(mop.getM_hp());
      System.out.println("mop_ex_name: " + mop.getM_name());
      System.out.println("mop_ex_hp: " + mop.getM_hp());
      System.out.println("mop_ex_ad: " + mop.getM_ad());
      System.out.println("mop_ex_armor: " + mop.getM_armor());
      System.out.println("mop_ex_level: " + mop.getM_level());
      System.out.println("mop_ex_stage_val: " + mop.getStage_val());
      //MopVo mop = new MopVo();
      
      // 아이템 적용
//      System.out.println("--------------적용전---------------");
//      System.out.println("main_ch_hp: " + main_ch.getC_hp());
//      System.out.println("main_ch_armor: " + main_ch.getC_armor());
//      System.out.println("main_ch_hp_percent: " + main_ch.getC_hp_percent());
//      System.out.println("main_ch_ad_percent: " + main_ch.getC_ad_percent());
//      System.out.println("main_ch_ap_percent: " + main_ch.getC_ap_percent());
//      System.out.println("main_ch_armor_percent: " + main_ch.getC_armor_percent());
//      System.out.println("--------------적용후---------------");
      main_ch.item_percent_apply();
//      System.out.println("main_ch_hp: " + main_ch.getC_hp());
//      System.out.println("main_ch_armor: " + main_ch.getC_armor());
      // mop = mop_dao.selectOne(stage_val);
      ///////////////////////////////////////
      // 실험용 몹 정보
      /*
      mop.setM_idx(1);
      mop.setM_name("몬스터1");
      mop.setM_level("일반");
      mop.setM_hp(400);
      mop.setM_ad(10);
      mop.setM_armor(0);
      mop.setM_skill("몹 스킬");
      */
      ///////////////////////////////////////

      // *main_ch를 복사한 객체를 addAttribute 해야될듯
      CharacterVo copy_main_ch = null;
      if (main_ch.getC_idx() == 1) {// 전사일때
         copy_main_ch = new Warrior();
      } else if (main_ch.getC_idx() == 2) {// 궁수일때
         copy_main_ch = new Archer();
      } else {// 법사일때
         copy_main_ch = new Wizard();
      }
      BeanUtils.copyProperties(main_ch, copy_main_ch);
//      System.out.println("-----------복사된 CharacterVo------------");
//      System.out.println("hp: " + copy_main_ch.getC_hp());
//      System.out.println("name: " + copy_main_ch.getC_name());
//      System.out.println("ad: " + copy_main_ch.getC_ad());
//      System.out.println("ap: " + copy_main_ch.getC_ap());
//      System.out.println("armor: " + copy_main_ch.getC_armor());
//      System.out.println("critical: " + copy_main_ch.getC_critical());
//      System.out.println("avd: " + copy_main_ch.getC_avd());

      // copy_main_ch에 가져온 스킬 4개 레벨, 턴 적용
      
      int original_critical = main_ch.getC_critical();

      application.setAttribute("mop", mop);
      application.setAttribute("main_ch", main_ch);
      application.setAttribute("copy_main_ch", copy_main_ch);
      application.setAttribute("s_idx", s_idx);
      application.setAttribute("s_num", s_num);
      application.setAttribute("original_critical", original_critical);
      

      model.addAttribute("mop", mop);
      model.addAttribute("main_ch", copy_main_ch);
      model.addAttribute("s_idx", s_idx);
      model.addAttribute("s_num", s_num);
      model.addAttribute("original_critical", original_critical);

      return "game/dungeon/dungeon_skeleton01";
   }

   @RequestMapping(value = "battle/attack.do")
   @ResponseBody
   public Map battle_attack(int s_idx) {

      CharacterVo main_ch = (CharacterVo) application.getAttribute("copy_main_ch");
      MopVo mop = (MopVo) application.getAttribute("mop");
      AttackVo attack_mop_vo = new AttackVo();
      AttackVo attack_main_ch_vo = new AttackVo();
      
      // 몬스터에게 cc, 도트뎀, 디버프 적용하기, 스킬 쿨 줄이기////////////
      String extra_skill_mop = mop.extra_skill();
      String extra_skill_main_ch = main_ch.extra_skill();
      
      //System.out.printf("%s %d", "기존 체력: ", original_hp);

      if (!extra_skill_mop.equals("cc")) {
         // 캐릭터에게 피해 입히기
         mop.attack_character(main_ch, attack_main_ch_vo);
      }

      if (!extra_skill_main_ch.equals("cc")) {
         // 몬스터에게 피해 입히기
         main_ch.attack_mop(main_ch, mop, attack_mop_vo, s_idx);
      }

      application.setAttribute("copy_main_ch", main_ch);
      application.setAttribute("mop", mop);

      JSONObject json = new JSONObject();
//      System.out.println("-----------AttackMopVo------------");
//      System.out.println("damage: " + attack_mop_vo.getDamage());
//      System.out.println("self_damage: " + attack_mop_vo.getSelf_damage());
//      System.out.println("damage_reduced: " + attack_mop_vo.getDamage_reduced());
//      System.out.println("damage_reduced_turn: " + attack_mop_vo.getDamage_reduced_turn());
//      System.out.println("ad_increased: " + attack_mop_vo.getAd_increased());
//      System.out.println("armor_increased: " + attack_mop_vo.getArmor_increased());
//      System.out.println("ad_reduced: " + attack_mop_vo.getAd_reduced());
//      System.out.println("armor_reduced: " + attack_mop_vo.getArmor_reduced());
//      System.out.println("dot_damage: " + attack_mop_vo.getDot_damage());
//      System.out.println("dot_damage_turn: " + attack_mop_vo.getDot_damage_turn());
//      System.out.println("cc_turn: " + attack_mop_vo.getCc_turn());
//      System.out.println("cc_name: " + attack_mop_vo.getCc_name());
//      System.out.println("battle_info: " + attack_mop_vo.getBattle_info());

//      System.out.println("-----------AttackMainChVo------------");
//      System.out.println("damage: " + attack_main_ch_vo.getDamage());
//      System.out.println("self_damage: " + attack_main_ch_vo.getSelf_damage());
//      System.out.println("damage_reduced: " + attack_main_ch_vo.getDamage_reduced());
//      System.out.println("damage_reduced_turn: " + attack_main_ch_vo.getDamage_reduced_turn());
//      System.out.println("ad_increased: " + attack_main_ch_vo.getAd_increased());
//      System.out.println("armor_increased: " + attack_main_ch_vo.getArmor_increased());
//      System.out.println("ad_reduced: " + attack_main_ch_vo.getAd_reduced());
//      System.out.println("armor_reduced: " + attack_main_ch_vo.getArmor_reduced());
//      System.out.println("dot_damage: " + attack_main_ch_vo.getDot_damage());
//      System.out.println("dot_damage_turn: " + attack_main_ch_vo.getDot_damage_turn());
//      System.out.println("cc_turn: " + attack_main_ch_vo.getCc_turn());
//      System.out.println("cc_name: " + attack_main_ch_vo.getCc_name());
//      System.out.println("battle_info: " + attack_main_ch_vo.getBattle_info());

//      System.out.println("-----------CharacterVo------------");
//      System.out.println("hp: " + main_ch.getC_hp());
//      System.out.println("name: " + main_ch.getC_name());
//      System.out.println("ad: " + main_ch.getC_ad());
//      System.out.println("ap: " + main_ch.getC_ap());
//      System.out.println("armor: " + main_ch.getC_armor());
//      System.out.println("critical: " + main_ch.getC_critical());
//      System.out.println("avd: " + main_ch.getC_avd());

//      System.out.println("-----------MopVo------------");
//      System.out.println("name: " + mop.getM_name());
//      System.out.println("hp: " + mop.getM_hp());
//      System.out.println("ad: " + mop.getM_ad());
//      System.out.println("armor: " + mop.getM_armor());
//      System.out.println("level: " + mop.getM_level());

//      json.put("main_ch", main_ch);
//      json.put("mop1", mop1);
//      json.put("attack_info", attack_mop_vo);

      // Map으로 정보 보내기
      Map map = new HashMap();
      map.put("main_ch", main_ch);
      map.put("mop", mop);
      map.put("attack_mop_vo", attack_mop_vo);
      map.put("attack_main_ch_vo", attack_main_ch_vo);
      map.put("extra_skill_main_ch", extra_skill_main_ch);
      map.put("extra_skill_mop", extra_skill_mop);

      return map;
   }

}