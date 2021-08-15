package io.codeblaze.cortex.engine.core;

import lombok.Getter;

public class Timer {

    @Getter
    private double lastLoopTime;

    public void init() {
        lastLoopTime = getTime();
    }

    public double getTime() {
        return System.nanoTime() / 1_000_000_000.0;
    }

    public float getElapsedTime() {
        var time = getTime();
        var elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

}
