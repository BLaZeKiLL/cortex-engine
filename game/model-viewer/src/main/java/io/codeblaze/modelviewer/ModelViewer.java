package io.codeblaze.modelviewer;

import io.codeblaze.cortex.engine.entities.Camera;
import io.codeblaze.cortex.engine.entities.Light;
import io.codeblaze.cortex.engine.entities.ModelEntity;
import io.codeblaze.cortex.engine.resource.Material;
import io.codeblaze.cortex.engine.resource.Model;
import io.codeblaze.cortex.engine.runtime.GameContext;
import io.codeblaze.cortex.engine.runtime.IGame;
import io.codeblaze.cortex.engine.core.Window;

import io.codeblaze.cortex.engine.shader.LitShaderProgram;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;

public class ModelViewer implements IGame {

    private GameContext context;

    private ModelEntity entity;
    private Light light;
    private Camera camera;

    private final Vector3f direction = new Vector3f(0, 0, 0);

    @Override
    public void init(GameContext context) throws Exception {
        this.context = context;

        var shader = new LitShaderProgram(this.context.getImporter().importShader("Shader/Lit.glsl"));

        this.context.getRenderer().init(shader, new Vector4f(1f, 1f, 1f, 1f));
    }

    @Override
    public void start() throws Exception {
        var model = new Model(
                context.getImporter().importModel("Model/dragon.obj"),
                new Material(context.getImporter().importTexture("Texture/black.png"), 10, 1)
        );

        entity = new ModelEntity(model, new Vector3f(0, -5, -20), new Vector3f(0, 0, 0), 1);
        light = new Light(new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 0.1f);
    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW.GLFW_KEY_W)) {
            direction.z = -1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_S)) {
            direction.z = 1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_A)) {
            direction.x = -1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_D)) {
            direction.x = 1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT_CONTROL)) {
            direction.y = -1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            direction.y = 1;
        }
    }

    @Override
    public void update(float delta) {
        camera.move(direction.mul(camera.getSpeed()));

        entity.rotate(0, 1f, 0);
    }

    @Override
    public void render(Window window) {
        if (window.isResized()) {
            GL33.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        context.getRenderer().process(entity);

        context.getRenderer().render(light, camera);
    }

    @Override
    public void cleanUp() {

    }

}
