package io.codeblaze.cortex.engine.renderer;

import io.codeblaze.cortex.engine.core.Window;
import io.codeblaze.cortex.engine.entities.Camera;
import io.codeblaze.cortex.engine.entities.Light;
import io.codeblaze.cortex.engine.entities.ModelEntity;
import io.codeblaze.cortex.engine.resource.MaterialModel;
import io.codeblaze.cortex.engine.shader.LitShaderProgram;

import io.codeblaze.cortex.engine.utils.MathUtils;
import lombok.Data;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CortexRenderer {

    private static final float FOV = 70.0f;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000.0f;

    private Window window;
    private LitShaderProgram shader;
    private Vector4f backgroundColor;

    private Matrix4f projectionMatrix;

    private final Map<MaterialModel, List<ModelEntity>> batches = new HashMap<>();

    public CortexRenderer(Window window) {
        this.window = window;

        GL33.glEnable(GL33.GL_CULL_FACE);
        GL33.glCullFace(GL33.GL_BACK);
    }

    public void init(LitShaderProgram shader, Vector4f backgroundColor) {
        this.shader = shader;
        this.backgroundColor = backgroundColor;

        createProjectionMatrix();

        shader.install();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.uninstall();
    }

    public void process(ModelEntity entity) {
        var model = entity.getModel();

        var batch = batches.get(model);

        if (batch != null) {
            batch.add(entity);
        } else {
            List<ModelEntity> newBatch = new ArrayList<>();

            newBatch.add(entity);

            batches.put(model, newBatch);
        }

    }

    public void render(Light sun, Camera camera) {
        prepare();

        shader.install();

        shader.loadLight(sun);
        shader.loadViewMatrix(camera);

        renderBatches();

        shader.uninstall();

        batches.clear();
    }

    public void cleanUp() {
        shader.cleanUp();
    }

    private void prepare() {
        GL33.glEnable(GL33.GL_DEPTH_TEST);
        GL33.glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);
        GL33.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, backgroundColor.w);
    }

    private void renderBatches() {
        for (MaterialModel model : batches.keySet()) {
            bindModel(model);

            var batch = batches.get(model);

            for (var entity : batch) {
                prepareEntity(entity);

                GL33.glDrawElements(GL33.GL_TRIANGLES, model.getRawModel().getTriangleCount(), GL33.GL_UNSIGNED_INT, 0);
            }

            unbindModel();
        }
    }

    private void prepareEntity(ModelEntity entity) {
        shader.loadTransformationMatrix(MathUtils.createTransformationMatrix(entity.getPosition(), entity.getRotation(), entity.getScale()));
    }

    private void bindModel(MaterialModel model) {
        GL33.glBindVertexArray(model.getRawModel().getVaoId());
        GL33.glEnableVertexAttribArray(0);
        GL33.glEnableVertexAttribArray(1);
        GL33.glEnableVertexAttribArray(2);

        shader.loadMaterial(model.getMaterial());

        GL33.glActiveTexture(GL33.GL_TEXTURE0);
        GL33.glBindTexture(GL33.GL_TEXTURE_2D, model.getMaterial().getTexture().getId());
    }

    private void unbindModel() {
        GL33.glDisableVertexAttribArray(0);
        GL33.glDisableVertexAttribArray(1);
        GL33.glDisableVertexAttribArray(2);
        GL33.glBindVertexArray(0);
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) window.getWidth() / (float) window.getHeight();
        float y_scale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio;
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((FAR_PLANE + NEAR_PLANE) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * NEAR_PLANE * FAR_PLANE) / frustum_length));
        projectionMatrix.m33(0);
    }
}
