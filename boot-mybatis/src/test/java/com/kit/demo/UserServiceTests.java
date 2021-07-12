package com.kit.demo;

import com.kit.demo.model.UserDomain;
import com.kit.demo.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void test() {
        UserDomain user = new UserDomain();
        user.setUserId(1);
        userService.addUser(user);
    }

}
