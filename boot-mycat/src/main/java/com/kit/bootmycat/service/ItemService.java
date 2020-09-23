package com.kit.bootmycat.service;

import java.util.List;

import com.lwl.boot.model.Item;

public interface ItemService {
	
	public void add(Item i);
	
	List<Item> find();
	
}
