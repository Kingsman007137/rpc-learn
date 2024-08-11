package com.kingsman.example.common.service;

import com.kingsman.example.common.model.User;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 获取用户名称
     *
     * @param user
     * @return
     */
    User getName(User user);
}
