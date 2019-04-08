package com.sikiedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sikiedu.bean.ItemInfo;
import com.sikiedu.service.ItemService;

/**
* @author ����
* @version ����ʱ�䣺2019��1��29�� ����7:06:44
* ��˵��
*/

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	private ItemService itemService; 
	
	@RequestMapping("")
	public String list(Model model) {
		List<ItemInfo> itemList = itemService.selectItemInfoByVo(null);
		model.addAttribute("itemList",itemList);
		return "index";
	}
	
	@RequestMapping("/sort")
	@ResponseBody
	public List<ItemInfo> sort(String name,Integer num){
		
		 List<ItemInfo> itemList = itemService.selectItemInfoSortByFlag(name,num);
		 return itemList;
	}
}
