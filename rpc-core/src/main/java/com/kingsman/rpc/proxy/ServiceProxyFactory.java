package com.kingsman.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂（用于创建代理对象）
 */
public class ServiceProxyFactory {
    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass 服务类
     * @param <T>          服务类泛型
     * @return 服务代理
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        // Proxy.newProxyInstance() 方法用于生成代理对象
        return (T) Proxy.newProxyInstance(
                // 获取服务类的类加载器，代理对象将使用这个类加载器进行加载
                serviceClass.getClassLoader(),
                // 为代理对象指定要实现的接口，这里是传入的服务类本身（通常为接口）
                new Class[]{serviceClass},
                // 传入一个InvocationHandler实例（ServiceProxy），当代理对象的方法被调用时，
                // 实际的调用逻辑将由这个InvocationHandler处理
                new ServiceProxy()
        );
    }
}
