package io.codeblaze.cortex.engine.runtime;

import io.codeblaze.cortex.engine.core.Window;

public interface IGame {

    void start(GameContext context) throws Exception;

    void input(Window window);

    void update(float delta);

    void render(Window window);

    void cleanUp();

}
