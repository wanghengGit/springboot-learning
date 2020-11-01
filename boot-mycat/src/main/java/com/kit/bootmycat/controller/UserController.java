package com.kit.bootmycat.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.kit.bootmycat.model.Users;
import com.kit.bootmycat.service.UsersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

	@Resource
	private UsersService usersService;
	
	@RequestMapping("/user/add")
	public String add(String name) {
		Users u = new Users();
		u.setName(name).setIndate(new Date());
		usersService.add(u);
		return "插入成功";
	}
	
	@RequestMapping("/user/find")
	public List<Users> find() {
		return usersService.find();
	}
	
}
