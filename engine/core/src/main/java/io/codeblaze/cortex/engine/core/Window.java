package io.codeblaze.cortex.engine.core;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

import java.util.Objects;

import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    @Getter
    private final String title;

    @Getter
    private int width;

    @Getter
    private int height;

    @Getter
    @Setter
    private boolean vSync;

    @Getter
    @Setter
    private boolean resized = false;

    private long handle = NULL;

    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
    }

    public void init() {
        // Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints(); // optional, the current window hints are already the default
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); // the window will stay hidden after creation
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); // the window will be resizable
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);

        // Create Window
        handle = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);

        if (handle == NULL) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        // Resize callback
        GLFW.glfwSetFramebufferSizeCallback(handle, (window, width, height) -> {
            this.width = width;
            this.height = height;
            this.setResized(true);
        });

        // Set up a key callback. It will be called every time a key is pressed, repeated or released.
        GLFW.glfwSetKeyCallback(handle, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                GLFW.glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode videoMode = Objects.requireNonNull(GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor()));
        // Center our window
        GLFW.glfwSetWindowPos(
                handle,
                (videoMode.width() - width) / 2,
                (videoMode.height() - height) / 2
        );

        GLFW.glfwMakeContextCurrent(handle);

        if (this.isVSync()) {
            // Enable V-Sync
            GLFW.glfwSwapInterval(1);
        }

        // Show window
        GLFW.glfwShowWindow(handle);

        // Initialize OpenGL
        GL.createCapabilities();
    }

    public void setClearColor(float r, float g, float b, float a) {
        GL33.glClearColor(r, g, b, a);
    }

    public boolean isKeyPressed(int keyCode) {
        return GLFW.glfwGetKey(handle, keyCode) == GLFW.GLFW_PRESS;
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(handle);
    }

    public void update() {
        GLFW.glfwSwapBuffers(handle);
        GLFW.glfwPollEvents();
    }

}
