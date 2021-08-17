package io.codeblaze.modelviewer;

import io.codeblaze.cortex.engine.runtime.CortexEngine;

public class Main {

    public static void main(String[] args) {
        var game = new ModelViewer();
        var engine = new CortexEngine("Model Viewer", 1980, 1080, true, true, game);

        engine.start();
    }

}
