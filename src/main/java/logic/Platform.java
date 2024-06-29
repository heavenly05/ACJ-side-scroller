package logic;

import org.joml.Vector2f;
import org.joml.Vector3f;

import ACJ.Main;
import ACJ.Sprite;
import ACJ.texture.Texture;
import ACJ.util.GameFile;
import logic.collision.AABB;

public class Platform extends GameObject {

    public Platform(Vector3f position, Vector2f scale, float rotation){
        super(new Sprite(Main.DATA_CENTER.getTexturedModel("platform"), position, scale, rotation), null, position, scale, rotation);
        super.setAABB(new AABB(this, scale.div(2, new Vector2f())));
    }
    
}
