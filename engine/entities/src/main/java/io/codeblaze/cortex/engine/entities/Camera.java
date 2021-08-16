package io.codeblaze.cortex.engine.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joml.Vector3f;

@Data
@AllArgsConstructor
public class Camera {

    private Vector3f position;
    private Vector3f rotation;
    private float speed;

    public void update() {

    }

}
