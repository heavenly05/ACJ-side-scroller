package logic;

import ACJ.Sprite;
import ACJ.render.FinalRenderer;

public class World {

    private Character character;

    private Platform[] platforms;

    private Sprite background;

    private FinalRenderer renderer;

    public World(Sprite background, Character character, Platform...platforms){
        this.character = character;
        this.platforms = platforms;
        this.background = background;
        this.renderer = new FinalRenderer(this.character.getCamera());
        for(Platform platform : platforms){
            renderer.spriteRenderer.addSprite(platform.getSprite());
        }
        renderer.spriteRenderer.addSprite(character.getSprite());
        renderer.backgroundRenderer.setBackground(background);
    }

    public void update(long window){
        character.move(window);
        for(Platform platform : platforms){
            if(platform.getPosition().distanceSquared(character.getPosition()) <= 25){
                character.move(character.getAABB().collide(platform.getAABB()));
                //System.out.println(character.getAABB().collide(platform.getAABB()));
            }
        }
        render();
    }

    public void render(){
        renderer.render(this.character.getCamera());
    }
    
}
