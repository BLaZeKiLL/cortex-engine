package io.codeblaze.modelviewer;

import io.codeblaze.cortex.engine.core.Window;

public class Main {

    public static void main(String[] args) {
        var window = new Window("Model Viewer", 1280, 720, true);

        window.init();

        window.setClearColor(1f, 1f, 1f, 1f);

        while (!window.windowShouldClose()) {
            window.update();
        }
    }

}
