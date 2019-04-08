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
* @author 作者
* @version 创建时间：2019年1月26日 下午5:49:38
* 类说明 游戏相关service实现类
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
		//直接查询后未筛选的列表
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
					 
			//判断标签和平台字符串是否为空
		 if(vo.getItem_tagids() != null&& !vo.getItem_tagids().equals("")||
			vo.getItem_platform() != null&& !vo.getItem_platform().equals("")) {
			//将满足条件的结果放入过滤的列表中并返回
			 List<ItemInfo> filterList = new ArrayList<ItemInfo>();
			 //获取查询条件id的数组
			 String[] voTagids = vo.getItem_tagids().split("#");
			 String[] voPlatforms = vo.getItem_platform().split("#");
			 //遍历查询未筛选的列表
			 for(ItemInfo itemInfo:queryList) {
				 //如果这个标志位走到最后还是true，条件符合添加到filterList
				 boolean isContain = true;
				 //处理标签
				 if(!vo.getItem_tagids().equals("")) {
					 String[] itemInfoTagids = itemInfo.getItem_tagids().split("#");
					 List<String> itemInfoTagidsList = Arrays.asList(itemInfoTagids);
					 for (String voTag : voTagids) {
						//判断是否包含
						 isContain = itemInfoTagidsList.contains(voTag);
						 if(!isContain)
							 break;
					}
				 }
				 //处理平台
				 if(!vo.getItem_platform().equals("")&&isContain) {
					 String[] itemInfoPlatform = itemInfo.getItem_platform().split("#");
					 List<String> itemInfoPlatformList = Arrays.asList(itemInfoPlatform);
					 for (String voPlatform : voPlatforms) {
						//判断是否包含
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
			//处理图片
			//文件名字，扩展名，路径
			String name=System.currentTimeMillis()+"";
			
			String extName = FilenameUtils.getExtension(upload_image.getOriginalFilename());
			
			String path = "C:\\document\\upload\\";
			
			String fileName = name + "." + extName;
			//保存图片
			upload_image.transferTo(new File(path + fileName));
			//保存图片名称到ItemInfo对象中
			itemInfo.setItem_cap_image(fileName);
		}
		
		//修改日期格式
		String date = itemInfo.getItem_release_date();
		
		String newDateStr = MyUtils.DateConvert(date);
		
		itemInfo.setItem_release_date(newDateStr);
		
		//处理标志位不能为空，如果为空设置为false
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
