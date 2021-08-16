package io.codeblaze.modelviewer;

import io.codeblaze.cortex.engine.runtime.GameContext;
import io.codeblaze.cortex.engine.runtime.IGame;
import io.codeblaze.cortex.engine.core.Window;

import io.codeblaze.cortex.engine.shader.LitShaderProgram;
import org.joml.Math;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;

public class ModelViewer implements IGame {

    private GameContext context;

    private int direction = 0;
    private float color = 0.0f;

    @Override
    public void init(GameContext context) throws Exception {
        this.context = context;

        var shader = new LitShaderProgram(this.context.getImporter().importShader("Shader/Lit.glsl"));

        this.context.getRenderer().init(shader, new Vector4f(1f, 1f, 1f, 1f));
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW.GLFW_KEY_UP)) {
            direction = 1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
            direction = -1;
        } else {
            direction = 0;
        }
    }

    @Override
    public void update(float delta) {
        color += direction * 0.01f;
        color = Math.clamp(0f, 1f, color);
    }

    @Override
    public void render(Window window) {
        if (window.isResized()) {
            GL33.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        window.setClearColor(color, color, color, 1f);

        GL33.glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void cleanUp() {

    }

}
