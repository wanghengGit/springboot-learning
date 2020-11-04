package com.kit.bootmycat.service;

import com.kit.bootmycat.model.Users;

import java.util.List;


public interface UsersService {

	public void add(Users u);
	
	List<Users> find();
	
}
