package com.sikiedu.mapper;

import java.util.List;

import com.sikiedu.bean.SysDict;

/**
* @author ����
* @version ����ʱ�䣺2019��1��26�� ����9:14:12
* ��˵��
*/
public interface DictMapper {

	List<String> selectTagName(List<String> idList);
	//���ݴʵ�����id��ȡ���дʵ�����б�
	List<SysDict> selectSysDictByTypeId(String typeId);

	
	
	
}
