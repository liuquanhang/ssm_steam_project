package com.sikiedu.service;

import java.util.List;

import com.sikiedu.bean.SysDict;

/**
* @author ����
* @version ����ʱ�䣺2019��1��26�� ����9:10:18
* ��˵��
*/
public interface DictService {
	//���ݴʵ�id��ѯ���ض�Ӧ��dict_item_name
	List<String> selectTagName(List<String> idList);
	//���ݴʵ�����id��ȡ���дʵ����
	List<SysDict> selectSysDictByTypeId(String typeId);
	
	
}
