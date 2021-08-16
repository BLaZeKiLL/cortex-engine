#VERTEX
#version 330

struct V2F {
    vec2 uv0;
    vec3 surfaceNormal;
    vec3 toLightVector;
    vec3 toCameraVector;
};

in vec3 position;
in vec2 uv0;
in vec3 normal;

out V2F v2f;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPosition;

void main() {
    // Multiplycation order crucial
    vec4 worldPosition = transformationMatrix * vec4(position, 1.0);

    gl_Position = projectionMatrix * viewMatrix * worldPosition;

    v2f.uv0 = uv0;

    v2f.surfaceNormal = (transformationMatrix * vec4(normal, 0.0)).xyz;

    v2f.toLightVector = lightPosition - worldPosition.xyz;
    v2f.toCameraVector = (inverse(viewMatrix) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - worldPosition.xyz;
}

#FRAGMENT
#version 330

struct V2F {
    vec2 uv0;
    vec3 surfaceNormal;
    vec3 toLightVector;
    vec3 toCameraVector;
};

in V2F v2f;

out vec4 out_Colour;

uniform sampler2D textureSampler;
uniform vec3 lightColor;
uniform float shineDamper;
uniform float specularity;

void main() {
    vec3 unitNormal = normalize(v2f.surfaceNormal);
    vec3 unitLightVector = normalize(v2f.toLightVector);
    vec3 unitVectorToCamera = normalize(v2f.toCameraVector);
    vec3 lightDirection = -unitLightVector;

    float nDotl = dot(unitNormal, unitLightVector);
    float brightness = max(nDotl, 0.2); // min value = ambient lighting

    vec3 diffuse = brightness * lightColor;

    vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);

    float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
    specularFactor = max(specularFactor, 0.0);

    float dampedFactor = pow(specularFactor, shineDamper);
    vec3 finalSpecular = dampedFactor * specularity * lightColor;

    out_Colour = vec4(diffuse, 1.0) * texture(textureSampler, v2f.uv0) + vec4(finalSpecular, 1.0);
}