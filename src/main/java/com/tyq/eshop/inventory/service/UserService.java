package com.tyq.eshop.inventory.service;

import com.tyq.eshop.inventory.mapper.UserMapper;
import com.tyq.eshop.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tangyingqi
 * @date 2019-09-08
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserInfo(){
        return userMapper.findUserInfo();
    }

}
