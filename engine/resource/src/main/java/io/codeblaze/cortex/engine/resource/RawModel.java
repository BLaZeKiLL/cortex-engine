package io.codeblaze.cortex.engine.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RawModel {

    @Getter
    private final int vaoId;

    @Getter
    private final int vertexCount;

}
