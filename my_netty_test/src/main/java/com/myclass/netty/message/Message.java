package com.myclass.netty.message;

/**
 * https://blog.csdn.net/linsongbin1/article/details/77990882?reload
 * @author zhangzuizui
 * @date 2018/8/23 14:33
 */
public class Message {

    public static final byte REQUEST = 1;
    public static final byte RESPONSE = 2;
    public static final byte ONEWAY = 3;
    short version = 1;

    short type;
    byte[] data;

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
