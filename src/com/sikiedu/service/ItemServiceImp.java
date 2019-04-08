package com.sikiedu.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sikiedu.bean.ItemInfo;
import com.sikiedu.bean.ItemInfoVo;
import com.sikiedu.mapper.ItemMapper;
import com.sikiedu.utils.MyUtils;

/**
* @author ����
* @version ����ʱ�䣺2019��1��26�� ����5:49:38
* ��˵�� ��Ϸ���serviceʵ����
*/

@Service
public class ItemServiceImp implements ItemService {
	
	//mapper
	@Autowired
	private ItemMapper itemMapper;
	@Override
	public List<ItemInfo> selectAll() {
		
		return itemMapper.selectAll();
	}
	@Override
	public List<ItemInfo> selectItemInfoByVo(ItemInfoVo vo) {
		//ֱ�Ӳ�ѯ��δɸѡ���б�
		 List<ItemInfo> queryList = itemMapper.selectItemInfoByVo(vo);

		 if(vo == null) {
			 List<ItemInfo> queryListView = new ArrayList<ItemInfo>();
			 for (ItemInfo itemInfo : queryList) {
				if(itemInfo.getIs_enable()!=null&&itemInfo.getIs_enable()==true) {
					queryListView.add(itemInfo);
				}
			}
			 return queryListView;
		 }
					 
			//�жϱ�ǩ��ƽ̨�ַ����Ƿ�Ϊ��
		 if(vo.getItem_tagids() != null&& !vo.getItem_tagids().equals("")||
			vo.getItem_platform() != null&& !vo.getItem_platform().equals("")) {
			//�����������Ľ��������˵��б��в�����
			 List<ItemInfo> filterList = new ArrayList<ItemInfo>();
			 //��ȡ��ѯ����id������
			 String[] voTagids = vo.getItem_tagids().split("#");
			 String[] voPlatforms = vo.getItem_platform().split("#");
			 //������ѯδɸѡ���б�
			 for(ItemInfo itemInfo:queryList) {
				 //��������־λ�ߵ������true������������ӵ�filterList
				 boolean isContain = true;
				 //�����ǩ
				 if(!vo.getItem_tagids().equals("")) {
					 String[] itemInfoTagids = itemInfo.getItem_tagids().split("#");
					 List<String> itemInfoTagidsList = Arrays.asList(itemInfoTagids);
					 for (String voTag : voTagids) {
						//�ж��Ƿ����
						 isContain = itemInfoTagidsList.contains(voTag);
						 if(!isContain)
							 break;
					}
				 }
				 //����ƽ̨
				 if(!vo.getItem_platform().equals("")&&isContain) {
					 String[] itemInfoPlatform = itemInfo.getItem_platform().split("#");
					 List<String> itemInfoPlatformList = Arrays.asList(itemInfoPlatform);
					 for (String voPlatform : voPlatforms) {
						//�ж��Ƿ����
						 isContain = itemInfoPlatformList.contains(voPlatform);
						 if(!isContain)
							 break;
					}
				 }
				 if(isContain) {
					 filterList.add(itemInfo);
				 }
			 }
			 return filterList;
		 }else {
			 return queryList;
		 }
	}
	@Override
	public void save(ItemInfo itemInfo,MultipartFile upload_image) throws IOException, Exception {
		ItemInfo saveItemInfo = setItemInfo(itemInfo,upload_image);
		
		itemMapper.save(saveItemInfo);
	}
	@Override
	public ItemInfo selectItemInfoById(String id) {
		return itemMapper.selectItemInfoById(id);
		
	}
	@Override
	public void update(ItemInfo itemInfo) {
		itemMapper.update(itemInfo);
		
	}
	@Override
	public void deleteByLogic(String id, Boolean enable) {
		itemMapper.deleteByLogic(id,enable);
		
	}
	@Override
	public List<ItemInfo> selectItemInfoSortByFlag(String name, Integer num) {
		return itemMapper.selectItemInfoSortByFlag(name,num);
		
	}
	private ItemInfo setItemInfo(ItemInfo itemInfo,MultipartFile upload_image) throws Exception, IOException {
		if(upload_image != null && upload_image.getOriginalFilename().equals("")) {
			//����ͼƬ
			//�ļ����֣���չ����·��
			String name=System.currentTimeMillis()+"";
			
			String extName = FilenameUtils.getExtension(upload_image.getOriginalFilename());
			
			String path = "C:\\document\\upload\\";
			
			String fileName = name + "." + extName;
			//����ͼƬ
			upload_image.transferTo(new File(path + fileName));
			//����ͼƬ���Ƶ�ItemInfo������
			itemInfo.setItem_cap_image(fileName);
		}
		
		//�޸����ڸ�ʽ
		String date = itemInfo.getItem_release_date();
		
		String newDateStr = MyUtils.DateConvert(date);
		
		itemInfo.setItem_release_date(newDateStr);
		
		//�����־λ����Ϊ�գ����Ϊ������Ϊfalse
		if(itemInfo.getIs_hot()==null) {
			itemInfo.setIs_hot(false);
		}
		if(itemInfo.getIs_hot_sale()==null) {
			itemInfo.setIs_hot_sale(false);
		}
		if(itemInfo.getIs_free()==null) {
			itemInfo.setIs_free(false);
		}
		if(itemInfo.getIs_specials()==null) {
			itemInfo.setIs_specials(false);
		}
		if(itemInfo.getIs_upcoming()==null) {
			itemInfo.setIs_hot(false);
		}
		if(itemInfo.getIs_upcoming()==null) {
			itemInfo.setIs_hot(false);
		}
		if(itemInfo.getIs_new()==null) {
			itemInfo.setIs_new(false);
		}
		if(itemInfo.getIs_enable()==null) {
			itemInfo.setIs_enable(false);
		}
		return itemInfo;
	}
}
