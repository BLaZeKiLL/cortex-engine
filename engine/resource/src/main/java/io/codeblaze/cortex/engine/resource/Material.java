package io.codeblaze.cortex.engine.resource;

import lombok.Data;

@Data

public class Material {

    private final Texture texture;
    private final float shineDamper;
    private final float specularity;

}
