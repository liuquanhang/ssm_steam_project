package com.sikiedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sikiedu.bean.ItemInfo;
import com.sikiedu.bean.ItemInfoVo;

/**
* @author ����
* @version ����ʱ�䣺2019��1��26�� ����5:53:20
* ��˵��
*/
public interface ItemMapper {

	List<ItemInfo> selectAll();
//����vo��ѯ������iteminfo�б�
	List<ItemInfo> selectItemInfoByVo(ItemInfoVo vo);
	//������Ϸ
	void save(ItemInfo itemInfo);
	
	ItemInfo selectItemInfoById(String id);
	//������Ϸ��Ϣ
	void update(ItemInfo itemInfo);
	
	void deleteByLogic(@Param("id")String id,@Param("enable") Boolean enable);
	
	List<ItemInfo> selectItemInfoSortByFlag(@Param("name")String name, @Param("num")Integer num);

}
