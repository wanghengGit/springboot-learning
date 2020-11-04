package com.kit.bootmycat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.kit.bootmycat.mapper.UsersMapper;
import com.kit.bootmycat.model.Users;
import com.kit.bootmycat.service.UsersService;
import org.springframework.stereotype.Service;


@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Resource
	private UsersMapper usersMapper;
	
	@Override
	public void add(Users u) {
		usersMapper.add(u);
	}

	@Override
	public List<Users> find() {
		return usersMapper.find();
	}

	
	
	
	
}
