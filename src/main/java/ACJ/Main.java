package ACJ;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;

import ACJ.components.Vao;
import ACJ.models.Model;
import ACJ.models.TexturedModel;
import ACJ.render.SpriteRenderer;
import ACJ.texture.Texture;

public class Main{
  public static void main(String args[]){
    Window window = new Window(1080, 720, "Side-Scroller");
    window.init();
    GL.createCapabilities(); 
    Camera camera = new Camera(new Vector3f(0, 0, 10));
    camera.setOrthographic(1, -1, 1, -1, 0.001f, 1000);
    Vao square = new Vao(4);
    square.bind();
    square.storeData(Shapes.SQUARE_INDICES, Shapes.SQUARE_POS);
    square.unbind();
    Model model = new Model(square);
    TexturedModel texturedModel = new TexturedModel(model, new Texture());
    Sprite sprite = new Sprite(texturedModel, new Vector3f(0, 0, 0), new Vector2f(1), 0);
    SpriteRenderer renderer = new SpriteRenderer(camera);
    System.out.println(square.getIndexCount());
    renderer.addSprite(sprite);
    window.setCamera(camera);
    window.setRenderer(renderer);
    window.run(() -> {sprite.setRotation(sprite.getRotation() + 0);});
    //bruh im so slow - lmao - u aint
    
  }
}
