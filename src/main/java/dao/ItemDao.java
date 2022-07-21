package dao;

import java.util.List;

import vo.ItemVo;

public interface ItemDao {

	public List<ItemVo> selectList();
	public ItemVo selectOne(int i_idx);
	public List<ItemVo> selectList_stage(String i_level);
}
