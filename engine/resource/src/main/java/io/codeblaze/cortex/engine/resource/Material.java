package io.codeblaze.cortex.engine.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Material {

    @Getter
    private final int textureId;

    @Getter
    @Setter
    private float shineDamper;

    @Getter
    @Setter
    private float specularity;

}
