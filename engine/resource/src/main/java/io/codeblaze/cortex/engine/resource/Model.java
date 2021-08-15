package io.codeblaze.cortex.engine.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Model {

    @Getter
    private final RawModel rawModel;

    @Getter
    private final Material material;

}
