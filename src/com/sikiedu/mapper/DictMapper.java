package com.sikiedu.mapper;

import java.util.List;

import com.sikiedu.bean.SysDict;

/**
* @author 作者
* @version 创建时间：2019年1月26日 下午9:14:12
* 类说明
*/
public interface DictMapper {

	List<String> selectTagName(List<String> idList);
	//根据词典类型id获取所有词典对象列表
	List<SysDict> selectSysDictByTypeId(String typeId);

	
	
	
}
