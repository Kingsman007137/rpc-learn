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
    User getUser(User user);

    /**
     * 用于测试 Mock 接口返回值
     *
     * @return
     */
    default short getNumber() {
        return 1;
    }
}
