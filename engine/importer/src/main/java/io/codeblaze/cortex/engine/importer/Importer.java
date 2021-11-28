package io.codeblaze.cortex.engine.importer;

import io.codeblaze.cortex.engine.resource.GLContext;
import io.codeblaze.cortex.engine.resource.Mesh;
import io.codeblaze.cortex.engine.resource.Shader;
import io.codeblaze.cortex.engine.resource.Texture;

import lombok.Getter;
import lombok.NonNull;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class Importer {

    @Getter
    private final GLContext context;

    @Getter
    private final ClassLoader loader;

    public Importer(ClassLoader loader) {
        this.loader = loader;
        this.context = new GLContext();
    }

    public Mesh importModel(String path) throws Exception {
        return OBJImporter.importModel(load(path), context, path);
    }

    public Texture importTexture(String path) throws Exception {
        return TextureImporter.importTexture(load(path), context, path);
    }

    public Shader importShader(String path) throws Exception {
        return ShaderImporter.importShader(load(path), path);
    }

    public String getPath(String path) {
        return Objects.requireNonNull(loader.getResource(path)).getFile();
    }

    public void cleanUp() {
        context.cleanUp();
    }

    private InputStream load(@NonNull String path) throws FileNotFoundException {
        var stream = loader.getResourceAsStream(path);

        if (stream == null) {
            throw new FileNotFoundException("Resource not found at : " + path);
        }

        return stream;
    }

}
