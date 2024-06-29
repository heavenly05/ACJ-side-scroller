package logic;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import ACJ.Camera;
import ACJ.Sprite;
import logic.collision.AABB;

public class Character extends GameObject {

    private Camera camera;

    public Character(Camera camera, Sprite sprite, Vector3f position, Vector2f scale, float rotation) {
        super(sprite, null, position, scale, rotation);
        super.setAABB(new AABB(this, scale.div(2, new Vector2f())));
        this.camera = camera;
        //TODO Auto-generated constructor stub
    }

    public void move(long window){
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GLFW.GLFW_TRUE){
            move(0.1f, 0);
        }
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GLFW.GLFW_TRUE){
            move(-0.1f, 0);
        }
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_E) == GLFW.GLFW_TRUE){
            move(0, 0.1f);
        }
        if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_Q) == GLFW.GLFW_TRUE){
            move(0, -0.1f);
        }
    }

    @Override
    public void move(float x, float y){
        super.move(x, y);
        camera.getPosition().x = getPosition().x;
        camera.getPosition().y = getPosition().y;
    }

    public Camera getCamera(){
        return camera;
    }
    
}
