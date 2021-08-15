package io.codeblaze.cortex.engine.resource;

import lombok.Getter;
import org.lwjgl.opengl.GL33;

import java.nio.ByteBuffer;

public class Texture {

    @Getter
    private final int id;

    @Getter
    private final int width;

    @Getter
    private final int height;

    public Texture(int width, int height) {
        this.id = GL33.glGenTextures();
        this.width = width;
        this.height = height;
    }

    public void bind() {
        GL33.glBindTexture(GL33.GL_TEXTURE_2D, id);
    }

    public void setIntParameter(int name, int value) {
        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, name, value);
    }

    public void uploadData(int width, int height, ByteBuffer data) {
        uploadData(GL33.GL_RGBA8, width, height, GL33.GL_RGBA, data);
    }

    public void uploadData(int internalFormat, int width, int height, int format, ByteBuffer data) {
        GL33.glTexImage2D(GL33.GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, GL33.GL_UNSIGNED_BYTE, data);
    }

}
