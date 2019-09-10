package com.tyq.eshop.inventory.controller;

import com.tyq.eshop.inventory.model.User;
import com.tyq.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangyingqi
 * @date 2019-09-08
 */
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    public User getUserInfo() {
        return userService.findUserInfo();
    }
}
