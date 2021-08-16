package io.codeblaze.cortex.engine.runtime;

import io.codeblaze.cortex.engine.importer.Importer;
import lombok.Data;

@Data
public class GameContext {

    private final Importer importer;

    public void cleanUp() {
        importer.cleanUp();
    }

}
