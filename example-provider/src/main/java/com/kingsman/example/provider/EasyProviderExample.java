package com.kingsman.example.provider;

import com.kingsman.example.common.service.UserService;
import com.kingsman.rpc.registry.LocalRegistry;
import com.kingsman.rpc.server.HttpServer;
import com.kingsman.rpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // 本地注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 Web 服务器
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
