package com.superferryman.seialize;

import com.superferryman.seialize.impl.JsonSerializer;

/**
 * @Author superferryman
 * @Date 2019/4/22 10:48
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化算法
     * @return 返回具体的序列化算法标识
     */
    byte getSerializerAlgorithm();

    /**
     * 将传入的 java 对象转换为二进制
     * @param obj 需要转换的java对象
     * @return java对象对应的字节数组
     */
    byte[] serialize(Object obj);

    /**
     * 二进制转换成 java 对象
     * @param tClass 对象类
     * @param bytes 二进制字节数组
     * @param <T> 对象类型
     * @return 字节数组对应的 java 对象
     */
    <T> T deserialize(Class<T> tClass, byte[] bytes);
}
