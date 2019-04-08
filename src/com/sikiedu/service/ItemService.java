package com.sikiedu.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sikiedu.bean.ItemInfo;
import com.sikiedu.bean.ItemInfoVo;

/**
* @author 作者
* @version 创建时间：2019年1月26日 下午5:44:57
* 类说明
*/
public interface ItemService {
	//查询游戏
	List<ItemInfo> selectAll();
	//根据iteminfovo查询列表
	List<ItemInfo> selectItemInfoByVo(ItemInfoVo vo);
	//新增游戏
	void save(ItemInfo itemInfo, MultipartFile upload_image) throws IOException, Exception;
	//根据id查询一条游戏记录
	ItemInfo selectItemInfoById(String id);
	//修改游戏信息
	void update(ItemInfo itemInfo);
	//下架游戏
	void deleteByLogic(String id, Boolean enable);
	//根据标志位名称查询列表排序
	List<ItemInfo> selectItemInfoSortByFlag(String name, Integer num);
	
	
	
}
