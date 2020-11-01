package com.kit.bootmycat.service;

import com.kit.bootmycat.model.Item;

import java.util.List;


public interface ItemService {
	
	public void add(Item i);
	
	List<Item> find();
	
}
