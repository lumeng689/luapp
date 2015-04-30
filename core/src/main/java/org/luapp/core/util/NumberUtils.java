package org.luapp.core.util;

import java.nio.ByteBuffer;

public class NumberUtils {

    /**
     * int数值转换成byte数组
     * 
     * @param i
     * @return
     */
    public byte[] intToBytes(int i) {
        return ByteBuffer.allocate(4).putInt(i).array();
    }
}
