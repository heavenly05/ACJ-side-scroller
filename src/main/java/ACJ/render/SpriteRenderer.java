package ACJ.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ACJ.Camera;
import ACJ.Sprite;
import ACJ.models.TexturedModel;
import ACJ.shader.BaseShader;

public class SpriteRenderer {

    private BaseShader shader = new BaseShader();

    private Map<TexturedModel, List<Sprite>> sprites = new HashMap<TexturedModel, List<Sprite>>();

    public SpriteRenderer(Camera camera){
        shader.start();
        shader.setProjection(camera.getOrthographic());
        shader.stop();
    }

    public void render(Camera camera){
        shader.start();
        shader.setView(camera.getTransformation());
        for(TexturedModel texturedModel : sprites.keySet()){
            prepare(texturedModel);
            shader.setTexture0(texturedModel.getTexture());
            for(Sprite sprite : sprites.get(texturedModel)){
                shader.setModel(sprite.getTransformation());
                texturedModel.render();
            }
            unprepare(texturedModel);
        }
        shader.stop();
    }

    public void prepare(TexturedModel texturedModel){
        //nothing yet;
        texturedModel.bind();
    }

    public void unprepare(TexturedModel texturedModel){
        //nothing yet
        texturedModel.unbind();
    }

    public void addSprite(Sprite sprite){
        if(sprites.containsKey(sprite.getTexturedModel())){
            sprites.get(sprite.getTexturedModel()).add(sprite);
        }else{
            List<Sprite> list = new ArrayList<Sprite>();
            list.add(sprite);
            sprites.put(sprite.getTexturedModel(), list);
        }
    }
    
}
