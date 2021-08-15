package io.codeblaze.cortex.engine.utils;

import java.nio.*;

public class BufferUtils {

    public static ByteBuffer createByteBuffer(byte[] data) {
        var buffer = org.lwjgl.BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static ShortBuffer createShortBuffer(short[] data) {
        var buffer = org.lwjgl.BufferUtils.createShortBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static CharBuffer createCharBuffer(char[] data) {
        var buffer = org.lwjgl.BufferUtils.createCharBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static IntBuffer createIntBuffer(int[] data) {
        var buffer = org.lwjgl.BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static LongBuffer createLongBuffer(long[] data) {
        var buffer = org.lwjgl.BufferUtils.createLongBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static FloatBuffer createFloatBuffer(float[] data) {
        var buffer = org.lwjgl.BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static DoubleBuffer createDoubleBuffer(double[] data) {
        var buffer = org.lwjgl.BufferUtils.createDoubleBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

}
