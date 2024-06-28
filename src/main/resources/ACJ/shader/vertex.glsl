#version 330 core

layout (location = 0) in vec3 position;

out vec3 color;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 model;

void main(void) {

    gl_Position = projection * view * model * vec4(position, 1);
    color = vec3(view[3][0], view[3][1], view[3][2]);

}