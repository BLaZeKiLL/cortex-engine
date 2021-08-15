package io.codeblaze.cortex.engine.core;

public interface IGame {

    void start() throws Exception;

    void input(Window window);

    void update(float delta);

    void render(Window window);

}
