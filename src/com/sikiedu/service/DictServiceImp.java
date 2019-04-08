package com.sikiedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sikiedu.bean.SysDict;
import com.sikiedu.mapper.DictMapper;

/**
* @author 作者
* @version 创建时间：2019年1月26日 下午9:11:41
* 类说明
*/
@Service
public class DictServiceImp implements DictService{
	
	@Autowired
	private DictMapper dictMapper;
	@Override
	public List<String> selectTagName(List<String> idList) {
		
		return dictMapper.selectTagName(idList);
	}
	@Override
	public List<SysDict> selectSysDictByTypeId(String typeId) {
		
		return dictMapper.selectSysDictByTypeId(typeId);
	}

}
