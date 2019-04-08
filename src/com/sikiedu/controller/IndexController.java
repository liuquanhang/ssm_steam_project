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
* @author 作者
* @version 创建时间：2019年1月29日 下午7:06:44
* 类说明
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
