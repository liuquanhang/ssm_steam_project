package com.sikiedu.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sikiedu.bean.ItemInfo;
import com.sikiedu.bean.ItemInfoVo;

/**
* @author ����
* @version ����ʱ�䣺2019��1��26�� ����5:44:57
* ��˵��
*/
public interface ItemService {
	//��ѯ��Ϸ
	List<ItemInfo> selectAll();
	//����iteminfovo��ѯ�б�
	List<ItemInfo> selectItemInfoByVo(ItemInfoVo vo);
	//������Ϸ
	void save(ItemInfo itemInfo, MultipartFile upload_image) throws IOException, Exception;
	//����id��ѯһ����Ϸ��¼
	ItemInfo selectItemInfoById(String id);
	//�޸���Ϸ��Ϣ
	void update(ItemInfo itemInfo);
	//�¼���Ϸ
	void deleteByLogic(String id, Boolean enable);
	//���ݱ�־λ���Ʋ�ѯ�б�����
	List<ItemInfo> selectItemInfoSortByFlag(String name, Integer num);
	
	
	
}
