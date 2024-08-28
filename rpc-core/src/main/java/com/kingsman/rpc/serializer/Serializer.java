package com.kingsman.rpc.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 */
public interface Serializer {
    /**
     * 序列化
     *
     * @param obj
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     *
     * @param data
     * @param type
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> type) throws IOException;
}
