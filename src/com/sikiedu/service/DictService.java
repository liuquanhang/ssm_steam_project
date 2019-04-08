package com.sikiedu.service;

import java.util.List;

import com.sikiedu.bean.SysDict;

/**
* @author 作者
* @version 创建时间：2019年1月26日 下午9:10:18
* 类说明
*/
public interface DictService {
	//根据词典id查询返回对应的dict_item_name
	List<String> selectTagName(List<String> idList);
	//根据词典类型id获取所有词典对象
	List<SysDict> selectSysDictByTypeId(String typeId);
	
	
}
