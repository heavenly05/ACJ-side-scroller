package ACJ.background;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import ACJ.Camera;
import ACJ.Sprite;

public class BackgroundRenderer {

    private BackgroundShader shader = new BackgroundShader();

    private Sprite background;

    public BackgroundRenderer(Camera camera){
        shader.start();
        shader.setProjection(camera.getOrthographic());
        shader.stop();
    }

    public void render(){
        GL11.glDepthMask(false);
        shader.start();
        shader.setView(new Matrix4f());
        background.getTexturedModel().bind();
        shader.setTexture0(background.getTexturedModel().getTexture());
        shader.setModel(background.getTransformation());
        background.getTexturedModel().render();
        background.getTexturedModel().unbind();
        shader.stop();
        GL11.glDepthMask(true);
    }

    public void setBackground(Sprite background){
        this.background = background;
    }
    
}
