package io.codeblaze.modelviewer;

import io.codeblaze.cortex.engine.core.CortexEngine;

public class Main {

    public static void main(String[] args) {
        var game = new ModelViewer();
        var engine = new CortexEngine("Model Viewer", 1280, 720, true, game);

        engine.start();
    }

}
