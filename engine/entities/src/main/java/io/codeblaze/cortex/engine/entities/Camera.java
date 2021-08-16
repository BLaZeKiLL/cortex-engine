package io.codeblaze.cortex.engine.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@Data
@AllArgsConstructor
public class Camera {

    private Vector3f position;
    private Vector3f rotation;
    private float speed;

    public void move(Vector3f delta) {
        position.add(delta);
    }

    public Matrix4f getViewMatrix() {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.rotateXYZ(Math.toRadians(rotation.x), Math.toRadians(rotation.y), Math.toRadians(rotation.z));
        viewMatrix.translate(new Vector3f(- position.x, - position.y, - position.z));
        return viewMatrix;
    }

}
