package io.codeblaze.cortex.engine.resource;

import lombok.Data;

@Data
public class Material {

    private final int textureId;
    private float shineDamper;
    private float specularity;

}
