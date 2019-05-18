package com.superferryman.protocol.common;

import java.io.File;
import java.io.Serializable;

/**
 * @Author superferryman
 * @Date 2019/5/10 14:57
 */
public class FileUploadFile implements Serializable {
    /**
     * 序列化版本标识
     */
    private static final long serialVersionUID = 1L;

    /**
     * 传输文件
     */
    private File file;

    /**
     * 文件名
     */
    private String fileMd5;

    /**
     * 开始位置
     */
    private int startPosition;

    /**
     * 文件字节数组
     */
    private byte[] bytes;

    /**
     * 结束位置
     */
    private int endPosition;

    /**
     * 文件总长
     */
    private Long length;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
