package io.codeblaze.cortex.engine.shader;

import io.codeblaze.cortex.engine.resource.Shader;
import lombok.NonNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;

import javax.naming.NameNotFoundException;

public abstract class ShaderProgram {

    private final int programId;
    private final Shader shader;

    public ShaderProgram(@NonNull Shader shader) throws NameNotFoundException {
        this.shader = shader;

        this.programId = GL33.glCreateProgram();

        GL33.glAttachShader(programId, shader.getVertexShaderId());
        GL33.glAttachShader(programId, shader.getFragmentShaderId());

        GL33.glLinkProgram(programId);
        GL33.glValidateProgram(programId);

        bindAttributes();

        init();
    }

    protected abstract void bindAttributes();

    protected abstract void init() throws NameNotFoundException;

    protected void bindAttribute(int attribute, String name) {
        GL33.glBindAttribLocation(programId, attribute, name);
    }

    protected int getUniformLocation(String name) throws NameNotFoundException {
        var result = GL33.glGetUniformLocation(programId, name);

        if (result == -1) {
            throw new NameNotFoundException("Uniform not found : " + name);
        }

        return result;
    }

    public void install() {
        GL33.glUseProgram(programId);
    }

    public void uninstall() {
        GL33.glUseProgram(0);
    }

    public void cleanUp() {
        GL33.glDetachShader(programId, shader.getVertexShaderId());
        GL33.glDetachShader(programId, shader.getFragmentShaderId());

        GL33.glDeleteShader(shader.getVertexShaderId());
        GL33.glDeleteShader(shader.getFragmentShaderId());

        GL33.glDeleteProgram(programId);
    }

    /**
     * Loads float value for the uniform in the current shader program
     * @param uniform uniform location
     * @param value value to bind
     */
    public static void loadUniformFloat(int uniform, float value) {
        GL33.glUniform1f(uniform, value);
    }

    /**
     * Loads boolean value for the uniform in the current shader program
     * @param uniform uniform location
     * @param value value to bind
     */
    public static void loadUniformBoolean(int uniform, boolean value) {
        GL33.glUniform1f(uniform, value ? 1 : 0);
    }

    /**
     * Loads vector value for the uniform in the current shader program
     * @param uniform uniform location
     * @param vector vector to bind
     */
    public static void loadUniformVector(int uniform, @NonNull Vector3f vector) {
        GL33.glUniform3f(uniform, vector.x, vector.y, vector.z);
    }

    /**
     * Loads Matrix4x4f value for the uniform in the current shader program
     * @param uniform uniform location
     * @param matrix matrix to bind
     */
    public static void loadUniformMatrix4x4f(int uniform, Matrix4f matrix) {
        GL33.glUniformMatrix4fv(uniform, false, matrix.get(BufferUtils.createFloatBuffer(16)));
    }
}
