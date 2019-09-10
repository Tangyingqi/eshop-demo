package com.tyq.eshop.inventory.mapper;

import com.tyq.eshop.inventory.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tangyingqi
 * @date 2019-09-08
 */
@Mapper
public interface UserMapper {

    public User findUserInfo();
}
