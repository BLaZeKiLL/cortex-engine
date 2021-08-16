package io.codeblaze.cortex.engine.entities;

import io.codeblaze.cortex.engine.resource.MaterialModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.joml.Vector3f;

@Data
@AllArgsConstructor
public class GameModel {

    private MaterialModel model;
    private Vector3f position;
    private Vector3f rotation;
    private float scale;

    public void translate(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void rotate(float dx, float dy, float dz) {
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }

}
