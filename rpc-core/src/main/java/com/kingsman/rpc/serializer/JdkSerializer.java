package com.kingsman.rpc.serializer;

import java.io.*;

public class JdkSerializer implements Serializer {
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 创建对象输出流，套字节输出流，
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.close();
        // 返回序列化后的字节数组
        return outputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> type) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            // 读取对象并返回
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed to deserialize object", e);
        } finally {
            objectInputStream.close();
        }
    }
}
