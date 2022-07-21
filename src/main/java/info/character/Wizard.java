package info.character;

import vo.AttackVo;
import vo.CharacterVo;
import vo.MopVo;

public class Wizard extends CharacterVo {

   public void active_skill1(MopVo mopVo, AttackVo attack_mop_vo) {
      // 아이스볼 10(1.5ap)
      System.out.println("아이스볼!");
      int damage = 10 + this.getC_ap() * 3 / 2;
      attack_mop_vo.setDamage(damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100);
      mopVo.setM_hp(mopVo.getM_hp() - damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100 );
      attack_mop_vo.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.", 
                                                         this.getC_name(), 
                                                         mopVo.getM_name(),
                                                         attack_mop_vo.getName(),
                                                         damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100));
   }

   public void active_skill2(MopVo mopVo, AttackVo attack_mop_vo) {
      // 썬더볼트 30(2ap)
      System.out.println("썬더볼트!");
      int damage = 30 + this.getC_ap() * 2;
      attack_mop_vo.setDamage(damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100);
      mopVo.setM_hp(mopVo.getM_hp() - damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100 );
      attack_mop_vo.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.", 
                                                         this.getC_name(), 
                                                         mopVo.getM_name(),
                                                         attack_mop_vo.getName(),
                                                         damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100));
   }

   public void active_skill3(MopVo mopVo, AttackVo attack_mop_vo) {
      // 메테오 50(2.5ap)
      System.out.println("메테오!");
      int damage = 50 + this.getC_ap() * 5 / 2;
      attack_mop_vo.setDamage(damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100);
      mopVo.setM_hp(mopVo.getM_hp() - damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100 );
      attack_mop_vo.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.", 
                                                         this.getC_name(), 
                                                         mopVo.getM_name(),
                                                         attack_mop_vo.getName(),
                                                         damage * (5000 / ( 50 + mopVo.getM_armor() ) ) / 100));
   }

   public void active_skill4(MopVo mopVo, AttackVo attack_mop_vo) {
      // 리퍼(전체 체력 비례 데미지, 일단 25%로 고정)
      System.out.println("리퍼!");
      // 추후 구현 예정
      attack_mop_vo.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.", 
                                                         this.getC_name(), 
                                                         mopVo.getM_name(),
                                                         attack_mop_vo.getName(),
                                                         mopVo.getM_hp() - mopVo.getM_hp() * (1 / 4)));
   }

   public void active_skill5(MopVo mopVo, AttackVo attack_mop_vo) {
      // 쉐도우실드(쉴드 생성, 10 + 2ap)
      System.out.println("리퍼!");
      int shiled = 10 + 2 * this.getC_ap();
      this.setC_hp(shiled);
   }

   public void active_skill6(MopVo mopVo, AttackVo attack_mop_vo) {
      // 인비저블(쉴드 생성, 10 + 2ap)
      System.out.println("인비저블!");
      int damage_reduced = 5;
      int damage_reduced_turn = 5;
      this.setDamage_reduced(getDamage_reduced()+damage_reduced);
      this.setDamage_reduced_turn(damage_reduced);
      attack_mop_vo.setDamage_reduced(damage_reduced_turn);
      attack_mop_vo.setDamage_reduced_turn(damage_reduced_turn);
      //return 6;
      attack_mop_vo.setBattle_info(String.format("%s(이)가  %s(으)로 %d턴동안 %d의 실드를 생성합니다.", 
                                                      this.getC_name(), 
                                                      attack_mop_vo.getName(),
                                                      damage_reduced_turn,
                                                      damage_reduced));
      
   }

   public void active_skill7(MopVo mopVo, AttackVo attack_mop_vo) {
      // 프로즌(2턴 동안 적을 얼림)
      System.out.println("프로즌!");
      attack_mop_vo.setCc_turn(2);
      attack_mop_vo.setBattle_info(String.format("%s(이)가  %s(으)로 %d턴동안 적을 얼립니다.", 
                                                      this.getC_name(), 
                                                      attack_mop_vo.getName(),
                                                      2));
                                                      
   }

   public void active_skill8(MopVo mopVo, AttackVo attack_mop_vo) {
      // 큐어링(10 + 2 * ap)
      System.out.println("큐어링!");
      this.setC_hp(getC_hp() + 10 + 2 * getC_ap());
      attack_mop_vo.setBattle_info(String.format("%s(이)가 자신의 hp를 %d만큼 회복하였습니다.", 
                                                   this.getC_name(), 
                                                   getC_hp() + 10 + 2 * getC_ap()));
      
   }

}