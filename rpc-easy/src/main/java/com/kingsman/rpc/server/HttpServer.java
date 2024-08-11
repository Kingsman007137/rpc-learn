package com.kingsman.rpc.server;

/**
 * 简易 HTTP 服务器接口
 */
public interface HttpServer {
    /**
     * 启动 HTTP 服务器
     *
     * @param port 服务器端口
     */
    void doStart(int port);
}
