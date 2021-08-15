package io.codeblaze.cortex.engine.importer;

import io.codeblaze.cortex.engine.resource.GLContext;
import io.codeblaze.cortex.engine.resource.RawModel;
import io.codeblaze.cortex.engine.resource.Shader;
import io.codeblaze.cortex.engine.resource.Texture;

import lombok.Getter;
import lombok.NonNull;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Importer {

    @Getter
    private final GLContext context;

    @Getter
    private final ClassLoader loader;

    public Importer(ClassLoader loader) {
        this.loader = loader;
        this.context = new GLContext();
    }

    public RawModel importModel(String path) throws Exception {
        return OBJImporter.importModel(load(path), context, path);
    }

    public Texture importTexture(String path) throws Exception {
        return TextureImporter.importTexture(load(path), context, path);
    }

    public Shader importShader(String path) throws Exception {
        return ShaderImporter.importShader(load(path), path);
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
