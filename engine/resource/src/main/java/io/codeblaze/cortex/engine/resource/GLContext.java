package io.codeblaze.cortex.engine.resource;

import io.codeblaze.cortex.engine.utils.BufferUtils;
import lombok.NonNull;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.util.ArrayList;
import java.util.List;

public class GLContext {

    private final List<Integer> vaos = new ArrayList<>();
    private final List<Integer> vbos = new ArrayList<>();
    private final List<Integer> ibos = new ArrayList<>();
    private final List<Integer> textures = new ArrayList<>();

    public RawModel bindRawModel(float[] vertices, int[] triangles, float[] uv0, float[] normals) {
        var vaoId = createVao();

        bindIndexBuffer(triangles);

        bindDataBuffer(0, 3, vertices);
        bindDataBuffer(1, 2, uv0);
        bindDataBuffer(2, 3, normals);

        GL33.glBindVertexArray(0); // Clear

        return new RawModel(vaoId, vertices.length, triangles.length);
    }

    public Texture bindTexture(@NonNull Texture texture) {
        textures.add(texture.getId());
        return texture;
    }

    public void cleanUp() {
        for (int vao : vaos) {
            GL33.glDeleteVertexArrays(vao);
        }
        for (int vbo : vbos) {
            GL33.glDeleteBuffers(vbo);
        }
        for (int ibo : ibos) {
            GL33.glDeleteBuffers(ibo);
        }
        for (int texture : textures) {
            GL33.glDeleteTextures(texture);
        }
    }

    private int createVao() {
        var vaoId = GL33.glGenVertexArrays();
        vaos.add(vaoId);

        GL33.glBindVertexArray(vaoId);

        return vaoId;
    }

    private void bindDataBuffer(int attribute, int size, float[] data) {
        int vboId = GL33.glGenBuffers();
        vbos.add(vboId);

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);

        var buffer = BufferUtils.createFloatBuffer(data);

        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, buffer, GL33.GL_STATIC_DRAW);

        GL33.glVertexAttribPointer(attribute, size, GL33.GL_FLOAT, false, 0, MemoryUtil.NULL);

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0); // Clear
    }

    private void bindIndexBuffer(int[] triangles) {
        var iboId = GL33.glGenBuffers();
        ibos.add(iboId);

        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, iboId);

        var buffer = BufferUtils.createIntBuffer(triangles);

        GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, buffer, GL33.GL_STATIC_DRAW);
    }

}
