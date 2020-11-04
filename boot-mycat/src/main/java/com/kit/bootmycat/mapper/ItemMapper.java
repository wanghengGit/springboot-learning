package com.kit.bootmycat.mapper;

import com.kit.bootmycat.model.Item;

import java.util.List;


public interface ItemMapper {

	public void add(Item i);
	
	List<Item> find();
	
}
