package io.codeblaze.cortex.engine.core;

public interface IGame {

    void init() throws Exception;

    void input(Window window);

    void update(float delta);

    void render(Window window);

}
