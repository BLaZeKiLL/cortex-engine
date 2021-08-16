package io.codeblaze.cortex.engine.importer;

import io.codeblaze.cortex.engine.resource.GLContext;
import io.codeblaze.cortex.engine.resource.RawModel;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OBJImporter {

    public static RawModel importModel(InputStream stream, GLContext context, String path) throws Exception {
        InputStreamReader fileReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;

        List<Vector3f> vertices = new ArrayList<>();
        List<Integer> triangles = new ArrayList<>();
        List<Vector2f> uv0 = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();

        float[] verticesArray;
        int[] trianglesArray;
        float[] normalsArray;
        float[] uv0Array;

        while ( true ) {
            line = reader.readLine();
            String[] currentLine = line.split(" ");

            if (line.startsWith("v ")) {
                Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                vertices.add(vertex);
            } else if (line.startsWith("vt ")) {
                Vector2f uv = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
                uv0.add(uv);
            } else if (line.startsWith("vn ")) {
                Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                normals.add(normal);
            } else if (line.startsWith("f ")) {
                uv0Array = new float[vertices.size()*2];
                normalsArray = new float[vertices.size()*3];
                break; // Start processing
            }
        }

        while ( line != null ) {
            if (!line.startsWith("f ")) {
                line = reader.readLine();
                continue;
            }

            String[] currentLine = line.split(" ");
            String[] vertex1 = currentLine[1].split("/");
            String[] vertex2 = currentLine[2].split("/");
            String[] vertex3 = currentLine[3].split("/");

            processVertex(vertex1,triangles,uv0,normals,uv0Array,normalsArray);
            processVertex(vertex2,triangles,uv0,normals,uv0Array,normalsArray);
            processVertex(vertex3,triangles,uv0,normals,uv0Array,normalsArray);

            line = reader.readLine();
        }

        reader.close();

        verticesArray = new float[vertices.size()*3];
        trianglesArray = new int[triangles.size()];

        int vertexPointer = 0;

        for (Vector3f vertex : vertices) {
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }

        for (int i = 0; i < triangles.size(); i++) {
            trianglesArray[i] = triangles.get(i);
        }

        return context.bindRawModel(verticesArray, trianglesArray, normalsArray, uv0Array);
    }

    private static void processVertex(String[] vertex, List<Integer> triangles, List<Vector2f> uv0, List<Vector3f> normals, float[] uv0Array, float[] normalsArray) {
        int currentVertexPointer = Integer.parseInt(vertex[0]) - 1;
        triangles.add(currentVertexPointer);

        Vector2f uv = uv0.get(Integer.parseInt(vertex[1]) - 1);
        uv0Array[currentVertexPointer*2] = uv.x;
        uv0Array[currentVertexPointer*2+1] = uv.y; // 1 - Y may be needed

        Vector3f currentNormal = normals.get(Integer.parseInt(vertex[2]) - 1);
        normalsArray[currentVertexPointer*3] = currentNormal.x;
        normalsArray[currentVertexPointer*3+1] = currentNormal.y;
        normalsArray[currentVertexPointer*3+2] = currentNormal.z;
    }

}
