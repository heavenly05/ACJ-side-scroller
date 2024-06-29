package logic.collision;

import org.joml.Vector2f;
import org.joml.Vector3f;

import logic.GameObject;

public class AABB {

    private Vector2f bounds;
    private GameObject object;

    public AABB(GameObject object, Vector2f bounds){
        this.object = object;
        this.bounds = bounds;
    }

    public void collide(AABB...others){
        Vector2f expel = new Vector2f();
        for(AABB other : others){
            expel.add(collide(other));
        }
        object.move(expel);
    }

    public boolean contains(Vector2f point){
        if(point.x > object.getPosition().x - bounds.x && point.x < object.getPosition().x + bounds.x){
            if(point.y > object.getPosition().y - bounds.y && point.y < object.getPosition().y + bounds.y){
                return true;
            }
        }
        return false;
    }

    public Vector2f collide(AABB other){
        if(other.object.getPosition().z == object.getPosition().z){
            float xd1 = other.object.getPosition().x + other.bounds.x - (object.getPosition().x - bounds.x);
            float xd2 = other.object.getPosition().x - other.bounds.x - (object.getPosition().x + bounds.x);//neg
            float yd1 = other.object.getPosition().y + other.bounds.y - (object.getPosition().y - bounds.y);
            float yd2 = other.object.getPosition().y - other.bounds.y - (object.getPosition().y + bounds.y);//neg
            if(xd1 > 0 && xd2 < 0){
                if(yd1 > 0 && yd2 < 0){
                    float x = xd1 < -xd2 ? xd1 : xd2;
                    float y = yd1 < -yd2 ? yd1 : yd2;
                    Vector3f delta = other.object.getPosition().sub(object.getPosition(), new Vector3f());
                    Vector2f delta2f = new Vector2f(delta.x, delta.y);
                    float yProjection = Math.abs(delta2f.dot(new Vector2f(0, 1)));
                    float xProjection = Math.abs(delta2f.dot(new Vector2f(1, 0)));
                    x = xProjection > yProjection ? x : 0;
                    y = yProjection > xProjection ? y : 0;
                    return new Vector2f(x, y);
                }
            }
        }
        return new Vector2f();
    }
    
}
