package io.codeblaze.cortex.engine.entities;

import io.codeblaze.cortex.engine.core.Window;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

@Data
@AllArgsConstructor
public class Camera {

    private Vector3f position;
    private Vector3f rotation;
    private float speed;

    public void update(Window window) {
        if (window.isKeyPressed(GLFW.GLFW_KEY_W)) {
            position.z -= speed;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_S)) {
            position.z += speed;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_D)) {
            position.x += speed;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_A)) {
            position.x -= speed;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            position.y += speed;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT_CONTROL)) {
            position.y -= speed;
        }
    }

    public Matrix4f getViewMatrix() {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.rotateXYZ(Math.toRadians(rotation.x), Math.toRadians(rotation.y), Math.toRadians(rotation.z));
        viewMatrix.translate(new Vector3f(- position.x, - position.y, - position.z));
        return viewMatrix;
    }

}
