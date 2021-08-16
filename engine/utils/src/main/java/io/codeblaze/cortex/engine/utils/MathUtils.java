package io.codeblaze.cortex.engine.utils;

import lombok.NonNull;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MathUtils {

    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation);
        matrix.rotateXYZ(toRadians(rotation));
        matrix.scale(scale);
        return matrix;
    }

    public static Vector3f toRadians(@NonNull Vector3f rotation) {
        return new Vector3f(Math.toRadians(rotation.x), Math.toRadians(rotation.y), Math.toRadians(rotation.z));
    }

}
