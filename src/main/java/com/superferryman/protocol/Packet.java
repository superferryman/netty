package com.superferryman.protocol;

/**
 * @Author superferryman
 * @Date 2019/4/22 10:39
 *
 * 基础包类
 *
 */
public abstract class Packet {
    /**
    * 协议版本
    * */
    private Byte version = 1;

    /**
    * 获取指令的方法
    * */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
