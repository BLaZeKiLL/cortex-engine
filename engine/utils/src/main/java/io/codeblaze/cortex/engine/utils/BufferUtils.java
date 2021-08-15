package io.codeblaze.cortex.engine.utils;

import lombok.NonNull;

import java.nio.*;

public class BufferUtils {

    public static ByteBuffer createByteBuffer(@NonNull byte[] data) {
        var buffer = org.lwjgl.BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static ShortBuffer createShortBuffer(@NonNull short[] data) {
        var buffer = org.lwjgl.BufferUtils.createShortBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static CharBuffer createCharBuffer(@NonNull char[] data) {
        var buffer = org.lwjgl.BufferUtils.createCharBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static IntBuffer createIntBuffer(@NonNull int[] data) {
        var buffer = org.lwjgl.BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static LongBuffer createLongBuffer(@NonNull long[] data) {
        var buffer = org.lwjgl.BufferUtils.createLongBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static FloatBuffer createFloatBuffer(@NonNull float[] data) {
        var buffer = org.lwjgl.BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static DoubleBuffer createDoubleBuffer(@NonNull double[] data) {
        var buffer = org.lwjgl.BufferUtils.createDoubleBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

}
