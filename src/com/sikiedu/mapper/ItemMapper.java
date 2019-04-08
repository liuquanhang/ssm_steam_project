package com.sikiedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sikiedu.bean.ItemInfo;
import com.sikiedu.bean.ItemInfoVo;

/**
* @author 作者
* @version 创建时间：2019年1月26日 下午5:53:20
* 类说明
*/
public interface ItemMapper {

	List<ItemInfo> selectAll();
//根据vo查询，返回iteminfo列表
	List<ItemInfo> selectItemInfoByVo(ItemInfoVo vo);
	//新增游戏
	void save(ItemInfo itemInfo);
	
	ItemInfo selectItemInfoById(String id);
	//更新游戏信息
	void update(ItemInfo itemInfo);
	
	void deleteByLogic(@Param("id")String id,@Param("enable") Boolean enable);
	
	List<ItemInfo> selectItemInfoSortByFlag(@Param("name")String name, @Param("num")Integer num);

}
