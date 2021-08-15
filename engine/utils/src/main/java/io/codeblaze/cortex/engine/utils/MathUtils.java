package io.codeblaze.cortex.engine.utils;

import lombok.NonNull;
import org.joml.Math;
import org.joml.Vector3f;

public class MathUtils {

    public static Vector3f toRadians(@NonNull Vector3f rotation) {
        return new Vector3f(Math.toRadians(rotation.x), Math.toRadians(rotation.y), Math.toRadians(rotation.z));
    }

}
