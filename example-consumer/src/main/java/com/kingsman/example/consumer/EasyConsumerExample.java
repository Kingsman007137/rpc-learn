package com.kingsman.example.consumer;

import com.kingsman.example.common.model.User;
import com.kingsman.example.common.service.UserService;
import com.kingsman.rpc.proxy.ServiceProxyFactory;

/**
 * 简易服务消费者示例
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("Kingsman");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println("获取用户成功：" + newUser.getName());
        } else {
            System.out.println("获取用户失败");
        }
    }
}
