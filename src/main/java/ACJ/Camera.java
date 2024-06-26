package ACJ;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera{

    public Vector2f position = new Vector2f();
    public Matrix4f orthographic = new Matrix4f();
  
    public Camera(Vector2f position){
        this.position = position;
    }

    public Vector2f getPosition(){
        return this.position;
    }

    public void setPosition(Vector2f position){
        this.position.set(position);
    }

    //view matrix
    public Matrix4f getTransformation(){
        Matrix4f identity = new Matrix4f();
        identity.translate(new Vector3f(-position.x(), -position.y(), 0));
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

}