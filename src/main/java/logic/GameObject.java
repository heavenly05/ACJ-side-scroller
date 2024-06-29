package logic;

import org.joml.Vector2f;
import org.joml.Vector3f;

import ACJ.Sprite;
import logic.collision.AABB;

public class GameObject {

    private Sprite sprite;
    private AABB collider;

    private Vector3f position;
    private Vector2f scale;
    private float rotation;

    public GameObject(Sprite sprite, AABB collider, Vector3f position, Vector2f scale, float rotation){
        this.sprite = sprite;
        this.collider = collider;
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public Vector3f getPosition(){
        return position;
    }

    public Vector2f getScale(){
        return scale;
    }

    public float getRotation(){
        return rotation;
    }

    public AABB getAABB(){
        return collider;
    }

    public void setAABB(AABB collider){
        this.collider = collider;
    }

    public void move(Vector2f translation){
        move(translation.x, translation.y);
    }

    public void move(float x, float y){
        this.position.add(x, y, 0);
        this.sprite.getPosition().x = position.x;
        this.sprite.getPosition().y = position.y;
    }

    public void scale(Vector2f scale){
        this.scale.set(scale);
        this.sprite.setScale(scale);
    }

    public void rotate(float rotation){
        this.rotation += rotation;
        this.sprite.setRotation(this.rotation);
    }
    
}
