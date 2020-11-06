package com.kit.bootmcep.controller;

import com.kit.bootmcep.dto.UserAvaterSaveInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping("/save")
    public void save(@RequestBody UserAvaterSaveInfo saveInfo) {
        System.out.println(saveInfo);
    }
}
