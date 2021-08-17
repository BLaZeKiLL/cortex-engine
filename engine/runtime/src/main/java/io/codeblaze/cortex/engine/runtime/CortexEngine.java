package io.codeblaze.cortex.engine.runtime;

import io.codeblaze.cortex.engine.core.Timer;
import io.codeblaze.cortex.engine.core.Window;

import io.codeblaze.cortex.engine.importer.Importer;

import io.codeblaze.cortex.engine.renderer.CortexRenderer;
import org.lwjgl.Version;

public class CortexEngine implements Runnable {

    public static final int TARGET_FPS = 120;
    public static final int TARGET_UPS = 60;

    private final Window window;
    private final IGame game;
    private final Timer timer;

    private final Thread gameThread;

    private GameContext gameContext;

    public CortexEngine(String title, int width, int height, boolean vSync, boolean fullScreen, IGame game) {
        this.gameThread = new Thread(this, "GAME_THREAD");
        this.window = new Window(title, width, height, vSync, fullScreen);
        this.timer = new Timer();
        this.game = game;
    }

    public void start() {
        gameThread.start();
    }

    @Override
    public void run() {
        try {
            init();
            loop();
            cleanUp();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    protected void init() throws Exception {
        window.init();

        System.out.println("Initialized LWJGL " + Version.getVersion());

        timer.init();

        System.out.println("Initialized Engine");

        createGameContext();

        System.out.println("Initialized Game Context");

        game.init(gameContext);

        System.out.println("Initialized Game");

        game.start();
    }

    protected void loop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        while (!window.shouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!window.isVSync()) {
                sync();
            }
        }
    }

    protected void cleanUp() {
        game.cleanUp();
        gameContext.cleanUp();
        window.cleanUp();
    }

    protected void input() {
        game.input(window);
    }

    protected void update(float delta) {
        game.update(delta);
    }

    protected void render() {
        game.render(window);
        window.update();
    }

    /**
     * Manual Sync
     */
    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;

        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {

            }
        }
    }

    private void createGameContext() {
        this.gameContext = new GameContext(
                new Importer(CortexEngine.class.getClassLoader()),
                new CortexRenderer(window)
        );
    }
}
