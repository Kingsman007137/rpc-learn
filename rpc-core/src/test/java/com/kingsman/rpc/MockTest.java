package com.kingsman.rpc;

import com.kingsman.example.common.model.User;
import com.kingsman.example.common.service.UserService;
import com.kingsman.rpc.proxy.ServiceProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MockTest {
    @Test
    public static void main(String[] args) {
        // 获取 代理
        UserService userService = ServiceProxyFactory.getMockProxy(UserService.class);
        short number = userService.getNumber();
        log.info("number:{}", number);
        User user = userService.getUser(new User());
        log.info("user:{}", user);
    }
}
