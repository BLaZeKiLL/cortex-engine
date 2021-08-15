package io.codeblaze.cortex.engine.importer;

import io.codeblaze.cortex.engine.resource.GLContext;
import io.codeblaze.cortex.engine.resource.Texture;
import io.codeblaze.cortex.engine.utils.BufferUtils;
import org.lwjgl.opengl.GL33;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class TextureImporter {

    public static Texture importTexture(InputStream stream, GLContext context, String path) throws IOException {
        ByteBuffer image;
        int width, height;

        try (MemoryStack stack = MemoryStack.stackPush()) {
            var w = stack.mallocInt(1);
            var h = stack.mallocInt(1);
            var channels = stack.mallocInt(1);

            var buffer = BufferUtils.createByteBuffer(stream.readAllBytes());

            image = STBImage.stbi_load_from_memory(buffer, w, h, channels, 4);

            if (image == null) {
                throw new IOException("Failed to load image : " + path);
            }

            width = w.get();
            height = h.get();
        }

        var texture = createTexture(width, height, image);

        return context.bindTexture(texture);
    }

    private static Texture createTexture(int width, int height, ByteBuffer data) {
        Texture texture = new Texture(width, height);

        // if Texture go weired look at the params
        texture.setIntParameter(GL33.GL_TEXTURE_WRAP_S, GL33.GL_REPEAT);
        texture.setIntParameter(GL33.GL_TEXTURE_WRAP_T, GL33.GL_REPEAT);
        texture.setIntParameter(GL33.GL_TEXTURE_MIN_FILTER, GL33.GL_LINEAR);
        texture.setIntParameter(GL33.GL_TEXTURE_MAG_FILTER, GL33.GL_LINEAR);

        texture.uploadData(width, height, data);

        return texture;
    }

}
