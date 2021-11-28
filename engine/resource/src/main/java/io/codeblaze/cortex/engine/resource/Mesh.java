package io.codeblaze.cortex.engine.resource;

import lombok.Data;

@Data
public class Mesh {

    private final int vaoId;
    private final int vertexCount;
    private final int triangleCount;

}
