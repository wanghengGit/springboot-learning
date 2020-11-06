package com.kit.bootmcep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangheng
 * @date 2020/11/6
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public void test() {
        System.out.println("hello world");
    }

    @PostMapping("/test2")
    public void test2() {
        System.out.println("hello world");
    }
}
