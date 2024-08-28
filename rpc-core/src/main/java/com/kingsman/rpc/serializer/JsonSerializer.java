package com.kingsman.rpc.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingsman.rpc.model.RpcRequest;
import com.kingsman.rpc.model.RpcResponse;

import java.io.IOException;

/**
 * JSON 序列化器
 */

/**
 * 在Java中,由于泛型擦除的原因,原始类型信息在编译时会被擦除掉。这意味着,当你使用泛型类型作为方法参数或返回值时,实际上传递或返回的是Object类型。
 *
 * 在这段代码中,RpcResponse的Data字段被声明为泛型类型T。在反序列化时,由于泛型擦除,Data字段实际上被反序列化为一个LinkedHashMap对象,而不是原来的对象类型。
 * 这个问题的关键在于:
 * 当你从字节数组中反序列化一个对象时,Jackson会将其反序列化为一个LinkedHashMap对象,而不是原来的对象类型。
 * 由于泛型擦除,原始对象类型信息在编译时已经丢失,所以无法直接将LinkedHashMap转换回原来的对象类型。
 *
 * 为了解决这个问题,这段代码中引入了handleResponse方法。它的作用是:
 * 先将rpcResponse.getData()序列化为字节数组。
 * 然后使用ObjectMapper.readValue()方法,将字节数组反序列化为rpcResponse.getDataType()指定的类型。
 * 最后将反序列化后的对象设置回rpcResponse的Data字段。
 * 最后返回类型为T的rpcResponse对象。
 *
 * 这样做的目的是,通过手动控制反序列化的过程,确保能够正确地还原原始对象类型,而不是简单的LinkedHashMap。
 * 这种特殊处理是因为Java泛型擦除带来的限制而产生的。
 *
 * 同理：在处理RpcRequest对象时,也需要进行类似的处理。这段代码中引入了handleRequest方法,用于处理RpcRequest对象的参数列表。
 * 获取RpcRequest中声明的参数类型数组(parameterTypes)和实际参数数组(args)。
 * 遍历每个参数,检查其实际类型是否与声明的类型不同。
 * 如果类型不同,则使用ObjectMapper重新将参数对象序列化为字节数组,然后反序列化为声明的类型。
 * 最后,将处理后的RpcRequest对象强制转换为目标类型T,并返回。
 */
public class JsonSerializer implements Serializer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes, clazz);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest) obj, clazz);
        }
        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse) obj, clazz);
        }
        return obj;
    }

    /**
     * 由于Object 的原始对象会被擦除，导致反序列化时 LinkedHashMap 无法转换为 原始对象，这里需要特殊处理
     *
     * @param rpcResponse
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T handleResponse(RpcResponse rpcResponse, Class<T> type) throws IOException {
        byte[] bytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(bytes, rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }

    /**
     * 由于Object 的原始对象会被擦除，导致反序列化时 LinkedHashMap 无法转换为 原始对象，这里需要特殊处理
     *
     * @param rpcRequest
     * @param type
     * @param <T>
     * @return
     */
    public <T> T handleRequest(RpcRequest rpcRequest, Class<T> type) throws IOException {
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();

        // 循环处理每个参数的类型
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> clazz = parameterTypes[i];
            // 如果类型不同，需要重新转换
            if (!clazz.isAssignableFrom(args[i].getClass())) {
                byte[] bytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(bytes, clazz);
            }
        }
        return type.cast(rpcRequest);
    }
}
