package io.codeblaze.cortex.engine.shader;

import io.codeblaze.cortex.engine.entities.Camera;
import io.codeblaze.cortex.engine.entities.Light;
import io.codeblaze.cortex.engine.resource.Material;
import io.codeblaze.cortex.engine.resource.Shader;
import lombok.NonNull;
import lombok.ToString;
import org.joml.Matrix4f;

import javax.naming.NameNotFoundException;

@ToString
public class LitShaderProgram extends ShaderProgram {

    private int uniform_TransformationMatrix;
    private int uniform_ProjectionMatrix;
    private int uniform_ViewMatrix;

    private int uniform_LightPosition;
    private int uniform_LightColor;

    private int uniform_ShineDamper;
    private int uniform_Specularity;

    public LitShaderProgram(@NonNull Shader shader) throws NameNotFoundException {
        super(shader);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "uv0");
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void init() throws NameNotFoundException {
        uniform_TransformationMatrix = super.getUniformLocation("transformationMatrix");
        uniform_ProjectionMatrix = super.getUniformLocation("projectionMatrix");
        uniform_ViewMatrix = super.getUniformLocation("viewMatrix");

        uniform_LightPosition = super.getUniformLocation("lightPosition");
        uniform_LightColor = super.getUniformLocation("lightColor");

        uniform_ShineDamper = super.getUniformLocation("shineDamper");
        uniform_Specularity = super.getUniformLocation("specularity");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        ShaderProgram.loadUniformMatrix4x4f(uniform_TransformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        ShaderProgram.loadUniformMatrix4x4f(uniform_ProjectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        ShaderProgram.loadUniformMatrix4x4f(uniform_ViewMatrix, camera.getViewMatrix());
    }

    public void loadLight(Light light) {
        ShaderProgram.loadUniformVector(uniform_LightPosition, light.getPosition());
        ShaderProgram.loadUniformVector(uniform_LightColor, light.getColor());
    }

    public void loadMaterial(Material material) {
        ShaderProgram.loadUniformFloat(uniform_ShineDamper, material.getShineDamper());
        ShaderProgram.loadUniformFloat(uniform_Specularity, material.getSpecularity());
    }

}
