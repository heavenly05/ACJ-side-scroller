package ACJ;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera{

    public Vector3f position = new Vector3f();
    public Matrix4f orthographic = new Matrix4f();
  
    public Camera(Vector3f position){
        this.position = position;
    }

    public Vector3f getPosition(){
        return this.position;
    }

    public void setPosition(Vector3f position){
        this.position.set(position);
    }

    //view matrix
    public Matrix4f getTransformation(){
        Matrix4f identity = new Matrix4f();
        identity.rotate(0, 0, 0, 0);
        identity.translate(position.negate(new Vector3f()));
        return identity;
    }

    //projection matrix
    /**
     * since the game is 2d, no projection is needed,
     * thus we use an orthographic matrix to make 
     * everything the same size, regardless of depth.
     * 
     * It is also used in GUIs
     */
    public Matrix4f getOrthographic(){
        return orthographic;
    }

    public void setOrthographic(float left, float right, float top, float bottom, float zNear, float zFar){
    orthographic.setOrtho(left, right, bottom, top, zNear, zFar);
    }

    public void move(long window){
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GLFW.GLFW_TRUE){
            position.add(0, 0, -0.1f);
        }
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GLFW.GLFW_TRUE){
            position.add(0, 0, 0.1f);
        }
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GLFW.GLFW_TRUE){
            position.add(0.1f, 0, 0);
        }
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GLFW.GLFW_TRUE){
            position.add(-0.1f, 0, 0);
        }
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_E) == GLFW.GLFW_TRUE){
            position.add(0, 0.1f, 0);
        }
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_Q) == GLFW.GLFW_TRUE){
            position.add(0, -0.1f, 0);
        }
    }

}