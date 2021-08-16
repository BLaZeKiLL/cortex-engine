package io.codeblaze.cortex.engine.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joml.Vector3f;

@Data
@AllArgsConstructor
public class Light {

    private Vector3f position;
    private Vector3f color;

}
