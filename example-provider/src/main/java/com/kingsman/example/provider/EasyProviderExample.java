package com.kingsman.example.provider;

import com.kingsman.rpc.server.HttpServer;
import com.kingsman.rpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // 启动 Web 服务器
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
