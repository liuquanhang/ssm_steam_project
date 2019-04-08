package com.sikiedu.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sikiedu.bean.ItemInfo;
import com.sikiedu.bean.ItemInfoVo;
import com.sikiedu.bean.SysDict;
import com.sikiedu.service.DictService;
import com.sikiedu.service.ItemService;
import com.sikiedu.utils.MyUtils;

/**
* @author ����
* @version ����ʱ�䣺2019��1��26�� ����5:24:27
* ��Ϸ���controller
*/
@Controller
@RequestMapping("/admin/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private DictService dictService;
	
	@Value("${dict.tagids}")
	private String dict_type_tagids;
	@Value("${dict.platform}")
	private String dict_type_platform;
	@Value("${dict.flag}")
	private String dict_type_flag;
	
	@RequestMapping("")
	public String list(Model model,ItemInfoVo vo) {
		
		//List<ItemInfo> itemList =  itemService.selectAll();
		List<ItemInfo> itemList =  itemService.selectItemInfoByVo(vo);
		
		List<SysDict> tagidsList= dictService.selectSysDictByTypeId(dict_type_tagids);
		
		List<SysDict> platformList= dictService.selectSysDictByTypeId(dict_type_platform);
		List<SysDict> flagList= dictService.selectSysDictByTypeId(dict_type_flag);
		
		model.addAttribute("itemList",itemList );
		//��ƽ̨,��ǩ�б�����model��
		model.addAttribute("tagidsList",tagidsList );
		model.addAttribute("platformList",platformList );
		model.addAttribute("flagList",flagList );
		//��vo������model��
		model.addAttribute("itemVo",vo);
		
		System.out.println(itemList);
		
		return "admin/item_list";
	}
	
	//���ݴʵ�id��ѯ���ض�Ӧ��dict_item_name
	@RequestMapping("/adminTags")
	@ResponseBody
	public List<String> tagName(@RequestBody List<String> idList){
		
		 List<String> nameList = dictService.selectTagName(idList);
		  
		 return nameList;
	}
	
	//�����Ϸ
	@RequestMapping("/save")
	@ResponseBody
	public String save(ItemInfo itemInfo,MultipartFile upload_image) throws Exception {
		
		itemService.save(itemInfo,upload_image);
		
		return "OK";
	}
	
	//�򿪱༭����
	@RequestMapping("/edit")
	@ResponseBody
	public ItemInfo edit(String id) {
		System.out.println("id ="+id);
		return itemService.selectItemInfoById(id);
	}
	
	//�޸���Ϸ
		@RequestMapping("/update")
		@ResponseBody
		public String update(ItemInfo itemInfo,MultipartFile upload_image) throws ParseException, IllegalStateException, IOException {
			System.out.println("itemInfo:"+itemInfo);
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
			
			itemService.update(itemInfo);
			
			return "OK";
		}
		
		//�߼�ɾ�� �¼�
		@RequestMapping("/delete")
		@ResponseBody
		public String delete(String id,Boolean enable) {
			itemService.deleteByLogic(id,enable);
			return "OK";
		}
		
	
}
