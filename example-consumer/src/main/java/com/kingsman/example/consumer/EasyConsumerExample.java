package com.kingsman.example.consumer;

import com.kingsman.example.common.model.User;
import com.kingsman.example.common.service.UserService;

/**
 * 简易服务消费者示例
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        // TODO 获取服务实现类的对象
        UserService userService = null;
        User user = new User();
        user.setName("Kingsman");
        User newUser = userService.getName(user);
        if (newUser != null) {
            System.out.println("获取用户成功：" + newUser.getName());
        } else {
            System.out.println("获取用户失败");
        }
    }
}
