package ACJ.render;

import ACJ.Camera;
import ACJ.background.BackgroundRenderer;

public class FinalRenderer {

    public SpriteRenderer spriteRenderer;
    public BackgroundRenderer backgroundRenderer;

    public FinalRenderer(Camera camera){
        spriteRenderer = new SpriteRenderer(camera);
        backgroundRenderer = new BackgroundRenderer(camera);
    }

    public void render(Camera camera){
        backgroundRenderer.render();
        spriteRenderer.render(camera);
    }
    
}
