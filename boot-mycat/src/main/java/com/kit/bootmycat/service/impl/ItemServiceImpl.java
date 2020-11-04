package com.kit.bootmycat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.kit.bootmycat.mapper.ItemMapper;
import com.kit.bootmycat.model.Item;
import com.kit.bootmycat.service.ItemService;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Resource
	private ItemMapper itemMapper;
	
	@Override
	public void add(Item i) {
		itemMapper.add(i);		
	}

	@Override
	public List<Item> find() {
		return itemMapper.find();
	}

	
	
}
