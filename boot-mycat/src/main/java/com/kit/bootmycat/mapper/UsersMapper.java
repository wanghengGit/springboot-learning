package com.kit.bootmycat.mapper;

import com.kit.bootmycat.model.Users;

import java.util.List;


public interface UsersMapper {

	
	void add(Users u);
	
	List<Users> find();
	
}
