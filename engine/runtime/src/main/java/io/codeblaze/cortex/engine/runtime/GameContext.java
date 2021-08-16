package io.codeblaze.cortex.engine.runtime;

import io.codeblaze.cortex.engine.importer.Importer;
import io.codeblaze.cortex.engine.renderer.CortexRenderer;
import lombok.Data;

@Data
public class GameContext {

    private final Importer importer;
    private final CortexRenderer renderer;

    public void cleanUp() {
        importer.cleanUp();
        renderer.cleanUp();
    }

}
