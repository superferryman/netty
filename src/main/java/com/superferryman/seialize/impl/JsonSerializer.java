package com.superferryman.seialize.impl;

import com.alibaba.fastjson.JSON;
import com.superferryman.seialize.Serializer;
import com.superferryman.seialize.SerializerAlogrithm;

/**
 * @Author superferryman
 * @Date 2019/4/22 10:54
 *
 * 现限定只有一种转换方式：JSON 序列化
 * 所以下列方法均写死 JSON
 */
public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> tClass, byte[] bytes) {
        return JSON.parseObject(bytes, tClass);
    }
}
