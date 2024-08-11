package com.kingsman.example.provider;

import com.kingsman.example.common.model.User;
import com.kingsman.example.common.service.UserService;

/**
 * 用户服务实现
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getName(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
