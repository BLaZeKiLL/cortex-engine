package io.codeblaze.cortex.engine.core;

import io.codeblaze.cortex.engine.importer.Importer;

public interface IGame {

    void start(GameContext context) throws Exception;

    void input(Window window);

    void update(float delta);

    void render(Window window);

    void cleanUp();

}
