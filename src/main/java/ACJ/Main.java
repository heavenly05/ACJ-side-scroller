package ACJ;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;

import ACJ.background.BackgroundRenderer;
import ACJ.components.Vao;
import ACJ.models.Model;
import ACJ.models.TexturedModel;
import ACJ.render.FinalRenderer;
import ACJ.render.SpriteRenderer;
import ACJ.texture.Texture;
import ACJ.util.GameFile;
import logic.Character;
import logic.DataCenter;
import logic.Platform;
import logic.World;

public class Main{

  public static final DataCenter DATA_CENTER = new DataCenter();
  public static void main(String args[]){
    Window window = new Window(1080, 720, "Side-Scroller");
    window.init();
    GL.createCapabilities(); 
    Camera camera = new Camera(new Vector3f(0, 0, 10));
    camera.setOrthographic(1, -1, 1, -1, 0.001f, 1000);
    Vao square = new Vao(4);
    square.bind();
    square.storeData(Shapes.SQUARE_INDICES, Shapes.SQUARE_POS, Shapes.SQUARE_TEXTURE_COORDS);
    square.unbind();
    Model model = new Model(square);
    Texture marioUgly = Texture.loadFromSTBI(new GameFile("images/marioUgly.png"), 1024);
    Texture backUgly = Texture.loadFromSTBI(new GameFile("images/backgroundUgly.png"), 1024);
    Texture dirtUgly = Texture.loadFromSTBI(new GameFile("images/dirtUgly.png"), 1024);
    TexturedModel texturedModel = new TexturedModel(model, marioUgly);
    TexturedModel background = new TexturedModel(model, backUgly);
    TexturedModel dirt = new TexturedModel(model, dirtUgly);
    DATA_CENTER.addTexture("marioUgly", marioUgly);
    DATA_CENTER.addTexture("backUgly", backUgly);
    DATA_CENTER.addTexture("dirtUgly", dirtUgly);
    DATA_CENTER.addModel("square", model);
    DATA_CENTER.addTexturedModel("platform", dirt);
    DATA_CENTER.addTexturedModel("mario", texturedModel);
    DATA_CENTER.addTexturedModel("background", background);
    Sprite back = new Sprite(background, new Vector3f(0, 0, -1), new Vector2f(2), 0);
    Sprite mar = new Sprite(texturedModel, new Vector3f(0, 0, 0), new Vector2f(1), 0);
    System.out.println(mar.getPosition());
    Character character = new Character(camera, mar, new Vector3f(0, 0, 0), new Vector2f(1), 0);
    Platform p0 = new Platform(new Vector3f(0, -1, 0), new Vector2f(1), 0);
    Platform p1 = new Platform(new Vector3f(-1, -1, 0), new Vector2f(1), 0);
    Platform p2 = new Platform(new Vector3f(-2, -1, 0), new Vector2f(1), 0);
    World world = new World(back, character, p0, p1, p2);
    window.run(() -> {world.update(window.getAddress()); System.out.print(mar.getPosition()); System.out.println(camera.getPosition());});
    //bruh im so slow - lmao - u aint
    
  }
}
